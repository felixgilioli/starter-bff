package br.com.felixgilioli.bff.doc;

/**
 * Represents an example image for documentation purposes,
 * containing the image source and its description.
 *
 * <p>
 * Use the {@link Builder} to create instances of this record fluently.
 * </p>
 *
 */
public class DocumentationExampleImage {

    private final String src;
    private final String description;

    private DocumentationExampleImage(String src, String description) {
        this.src = src;
        this.description = description;
    }

    /**
     * Returns the path or URL of the image.
     *
     * @return the image path or URL
     */
    public String getSrc() {
        return src;
    }

    /**
     * Returns the description of the image.
     *
     * @return the image description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Creates a new {@link Builder} instance for constructing
     * a {@link DocumentationExampleImage}.
     *
     * @return a new Builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for creating instances of {@link DocumentationExampleImage} in a fluent way.
     */
    public static class Builder {
        private String src;
        private String description;

        /**
         * Sets the image source path or URL.
         *
         * @param src the path or URL of the image
         * @return this builder instance
         */
        public Builder src(String src) {
            this.src = src;
            return this;
        }

        /**
         * Sets the image description.
         *
         * @param description the description of the image
         * @return this builder instance
         */
        public Builder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Builds a new {@link DocumentationExampleImage} instance with the set values.
         *
         * @return a new DocumentationExampleImage instance
         */
        public DocumentationExampleImage build() {
            return new DocumentationExampleImage(src, description);
        }
    }
}