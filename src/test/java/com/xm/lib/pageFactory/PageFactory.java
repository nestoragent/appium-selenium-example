package com.xm.lib.pageFactory;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.xm.lib.Page;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PageFactory {
    public String currentPageTitle;
    public Page currentPage;

    private final String pagesPackage;
    private final Map<String, Class<?>> pages;

    /**
     * <p>
     * Constructor for PageFactory.</p>
     *
     * @param pagesPackage a {@link String} object.
     */
    public PageFactory(String pagesPackage) {
        this.pages = new HashMap();
        this.pagesPackage = pagesPackage;
    }

    /**
     * Construct page object by title
     *
     * @param title page title
     * @return page instance
     * initialization error
     */
    public Page getPage(String title) throws PageInitializationException, AutotestError {
        if (null == currentPage || !currentPageTitle.equals(title)) {
            try {
                if (null != currentPage) {
                    currentPage = getPage(currentPage.getClass().getPackage().getName(), title);
                }

                if (null == currentPage) {
                    currentPage = getPage(pagesPackage, title);

                }
                if (null == currentPage) {

                    throw new AutotestError("Page Object with title " + title + " is not registered");
                }

            } catch (IllegalAccessException | InstantiationException | InvocationTargetException |
                     NoSuchMethodException e) {
                e.printStackTrace();
                throw new PageInitializationException("Page " + title + " initialization error.", e);
            }
        }
        return currentPage;
    }

    /**
     * Initialize page by class
     *
     * @param page
     * @return
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public Page getPage(Class<? extends Page> page) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Constructor<Page> c = ((Constructor<Page>) page.getConstructor());
        c.setAccessible(true);
        currentPage = c.newInstance();
        currentPageTitle = currentPage.getTitle();
        return currentPage;
    }

    /**
     * <p>
     *
     * @param packageName a {@link String} object.
     * @param title       a {@link String} object.
     * @throws IllegalAccessException    if any.
     * @throws NoSuchMethodException     if any.
     * @throws InvocationTargetException if any.
     * @throws InstantiationException    if any.
     */
    public Page getPage(String packageName, String title) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        if (pages.isEmpty()) {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            Set<Class<?>> allClasses = new HashSet<>();
            try {
                ImmutableSet<ClassPath.ClassInfo> classInfo = ClassPath.from(loader).getAllClasses();
                classInfo.stream().filter((info) -> (info.getName().startsWith(packageName + "."))).forEach((info) -> {
                    allClasses.add(info.load());
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (Class<?> page : allClasses) {
                String pageTitle = null;
                if (page.getName().contains("AnyPage")) {
                    continue;
                } else if (null != page.getAnnotation(PageEntry.class)) {
                    pageTitle = page.getAnnotation(PageEntry.class).title();
                } else {
                    try {
                        pageTitle = (String) FieldUtils.readStaticField(page, "title", true);
                    } catch (IllegalArgumentException e) {
                        //Skip it/ It is not a page object
                    }
                }
                pages.put(pageTitle, page);
            }
        }

        Constructor<Page> c;
        try {
            c = ((Constructor<Page>) pages.get(title).getConstructor());
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new AutotestError("Can not find a page with the title \"" + title + "\"");
        }

        c.setAccessible(true);
        currentPage = c.newInstance();
        currentPageTitle = title;
        return currentPage;
    }

    /**
     * <p>
     * Getter for the field <code>currentPage</code>.</p>
     *
     * @throws InvocationTargetException if any.
     */
    public Page getCurrentPage() throws InvocationTargetException {
        if (null == currentPage) {
            throw new InvocationTargetException(new Exception("Current page not initialized!"));
        } else {
            return currentPage;
        }
    }
}
