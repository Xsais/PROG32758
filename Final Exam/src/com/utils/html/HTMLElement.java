package com.utils.html;

import java.util.*;
import java.util.stream.Stream;

public class HTMLElement<T> {

    // Stores the name of the specified tag
    private String tagName;

    // Stores the content inside the tag
    private T innerContent;

    // Stores the attributes associated with the tag
    private List<Attribute> attributes = new ArrayList<>();

    /**
     * Creates a new HTML element setting the desired tagname
     *
     * @param tagName The desired Tagname for the element
     */
    public HTMLElement(String tagName) {

        setTagName(tagName);
    }

    /**
     * Creates a new HTML element setting the desired tagname and assigning an variable length of attributes
     *
     * @param tagName    The desired Tagname for the element
     * @param attributes The desired attributes to be assigned to the element
     */
    public HTMLElement(String tagName, Attribute... attributes) {

        this(tagName);
        addAttributes(Arrays.asList(attributes));
    }

    /**
     * Retrieves the inner content of the tag
     *
     * @return The currently defined inner content for the element
     */
    public T getInnerContent() {

        return innerContent;
    }

    /**
     * Assigns the desired inner content of the Element
     *
     * @param innerContent
     */
    public void setInnerContent(T innerContent) {

        this.innerContent = innerContent;
    }

    /**
     * Retrieves the current name of the element
     *
     * @return The current name of the element
     */
    public String getTagName() {

        return tagName;
    }

    /**
     * Changes the Tagname of the element
     *
     * @param tagName The desired tagname to be changed to
     */
    public void setTagName(String tagName) {

        this.tagName = tagName;
    }

    /**
     * Retrieves an array of the desired  attributes from the element by name
     *
     * @param name The desired name to search for
     * @return The attributes that matches the name
     */
    public Attribute[] getAttributes(String name) {

        Stream<Attribute> match = attributes.stream().filter(a -> a.getName().equals(name));

        if (match == null) {

            return null;
        }
        return match.toArray(Attribute[]::new);
    }

    /**
     * Retrieves the desired attribute giving a name
     *
     * @param name The desired name to search for
     * @return The attribute that matches the name
     */
    public Attribute getAttribute(String name) {

        Optional<Attribute> match = attributes.stream().filter(a -> a.getName().equals(name)).findFirst();

        if (match == null) {

            return null;
        }
        return match.get();
    }

    /**
     * Retrieves all active attributes for the element
     *
     * @return All attributes of the element
     */
    public Attribute[] getAttributes() {

        return attributes.toArray(new Attribute[0]);
    }

    /**
     * Adds the desired attribute to the element
     *
     * @param attribute The desired attribute to be added
     */
    public void addAttributes(Attribute attribute) {

        this.attributes.add(attribute);
    }

    /**
     * Adds a collection of attributes to the element
     *
     * @param attributes The desired attributes to be added
     */
    public void addAttributes(Collection<Attribute> attributes) {

        this.attributes.addAll(attributes);
    }

    /**
     * Retrieves the HTML code that represents the Element
     *
     * @return A representation of the Element in HTML
     */
    @Override
    public String toString() {

        StringBuilder html = new StringBuilder(String.format("<%s", tagName));

        for (int i = 0; i < attributes.size(); ++i) {

            html.append(String.format(" %s", attributes.get(i)));
        }

        if (innerContent != null) {

            html.append(">");

            html.append(innerContent);
            html.append(String.format("</%s>", tagName));
        } else {

            html.append("/>");
        }

        return html.toString();
    }
}
