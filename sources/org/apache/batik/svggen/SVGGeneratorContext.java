/*****************************************************************************
 * Copyright (C) The Apache Software Foundation. All rights reserved.        *
 * ------------------------------------------------------------------------- *
 * This software is published under the terms of the Apache Software License *
 * version 1.1, a copy of which has been included with this distribution in  *
 * the LICENSE file.                                                         *
 *****************************************************************************/

package org.apache.batik.svggen;

import org.w3c.dom.Document;

/**
 * This class contains all non graphical contextual information that
 * are needed by the {@link org.apache.batik.svggen.SVGGraphics2D} to
 * generate SVG from Java 2D primitives.
 * You can subclass it to change the defaults.
 *
 * @see org.apache.batik.svggen.SVGGraphics2D#SVGGraphics2D(SVGGeneratorContext,boolean)
 * @author <a href="mailto:cjolif@ilog.fr>Christophe Jolif</a>
 * @version $Id$
 */
public class SVGGeneratorContext implements ErrorConstants {
    // this fields are package access for read-only purpose

    /**
     * Factory used by this Graphics2D to create Elements
     * that make the SVG DOM Tree
     */
    Document domFactory;

    /**
     * Handler that defines how images are referenced in the
     * generated SVG fragment. This allows different strategies
     * to be used to handle images.
     * @see org.apache.batik.svggen.ImageHandler
     * @see org.apache.batik.svggen.ImageHandlerBase64Encoder
     * @see org.apache.batik.svggen.ImageHandlerPNGEncoder
     * @see org.apache.batik.svggen.ImageHandlerJPEGEncoder
     */
    ImageHandler imageHandler;

    /**
     * To deal with Java 2D extension (custom java.awt.Paint for example).
     */
    ExtensionHandler extensionHandler;

    /**
     * To generate consitent ids.
     */
    SVGIDGenerator idGenerator;

    /**
     * To set style.
     */
    StyleHandler styleHandler;

    /**
     * The comment to insert at generation time.
     */
    String generatorComment;

    /**
     * The error handler.
     */
    ErrorHandler errorHandler;

    /**
     * Builds an instance of <code>SVGGeneratorContext</code> with the given
     * <code>domFactory</code> but let the user set later the other contextual
     * information.
     * @see #setIDGenerator
     * @see #setExtensionHandler
     * @see #setImageHandler
     */
    protected SVGGeneratorContext(Document domFactory) {
        setDOMFactory(domFactory);
    }

    /**
     * Creates an instance of <code>SVGGeneratorContext</code> with the
     * given <code>domFactory</code> and with the default values for the
     * other information.
     * @see org.apache.batik.svggen.SVGIDGenerator
     * @see org.apache.batik.svggen.DefaultExtensionHandler
     * @see org.apache.batik.svggen.ImageHandlerBase64Encoder
     */
    public static SVGGeneratorContext createDefault(Document domFactory) {
        SVGGeneratorContext ctx = new SVGGeneratorContext(domFactory);
        ctx.setIDGenerator(new SVGIDGenerator());
        ctx.setExtensionHandler(new DefaultExtensionHandler());
        ctx.setImageHandler(new ImageHandlerBase64Encoder());
        ctx.setStyleHandler(new DefaultStyleHandler());
        ctx.setComment("Generated by the Batik Graphics2D SVG Generator");
        ctx.setErrorHandler(new DefaultErrorHandler());
        return ctx;
    }

    /**
     * Returns the {@link org.apache.batik.svggen.SVGIDGenerator} that
     * has been set.
     */
    final public SVGIDGenerator getIDGenerator() {
        return idGenerator;
    }

    /**
     * Sets the {@link org.apache.batik.svggen.SVGIDGenerator}
     * to be used. It should not be <code>null</code>.
     */
    final protected void setIDGenerator(SVGIDGenerator idGenerator) {
        if (idGenerator == null)
            throw new SVGGraphics2DRuntimeException(ERR_ID_GENERATOR_NULL);
        this.idGenerator = idGenerator;
    }

    /**
     * Returns the DOM Factory that
     * has been set.
     */
    final public Document getDOMFactory() {
        return domFactory;
    }

    /**
     * Sets the DOM Factory
     * to be used. It should not be <code>null</code>.
     */
    final protected void setDOMFactory(Document domFactory) {
        if (domFactory == null)
            throw new SVGGraphics2DRuntimeException(ERR_DOM_FACTORY_NULL);
        this.domFactory = domFactory;
    }

    /**
     * Returns the {@link org.apache.batik.svggen.ExtensionHandler} that
     * has been set.
     */
    final public ExtensionHandler getExtensionHandler() {
        return extensionHandler;
    }

    /**
     * Sets the {@link org.apache.batik.svggen.ExtensionHandler}
     * to be used. It should not be <code>null</code>.
     */
    final protected void setExtensionHandler(ExtensionHandler extensionHandler) {
        if (extensionHandler == null)
            throw new SVGGraphics2DRuntimeException(ERR_EXTENSION_HANDLER_NULL);
        this.extensionHandler = extensionHandler;
    }

    /**
     * Returns the {@link org.apache.batik.svggen.ImageHandler} that
     * has been set.
     */
    final public ImageHandler getImageHandler() {
        return imageHandler;
    }

    /**
     * Sets the {@link org.apache.batik.svggen.ImageHandler}
     * to be used. It should not be <code>null</code>.
     */
    final protected void setImageHandler(ImageHandler imageHandler) {
        if (imageHandler == null)
            throw new SVGGraphics2DRuntimeException(ERR_IMAGE_HANDLER_NULL);
        this.imageHandler = imageHandler;
    }

    /**
     * Returns the {@link org.apache.batik.svggen.StyleHandler} that
     * has been set.
     */
    final public StyleHandler getStyleHandler() {
        return styleHandler;
    }

    /**
     * Sets the {@link org.apache.batik.svggen.Stylehandler}
     * to be used. It should not be <code>null</code>.
     */
    final protected void setStyleHandler(StyleHandler styleHandler) {
        if (styleHandler == null)
            throw new SVGGraphics2DRuntimeException(ERR_STYLE_HANDLER_NULL);
        this.styleHandler = styleHandler;
    }

    /**
     * Returns the comment to be generated in the SVG file.
     */
    final public String getComment() {
        return generatorComment;
    }

    /**
     * Sets the comment to be used. It can be <code>null</code> if you
     * want to disable it.
     */
    final protected void setComment(String generatorComment) {
        this.generatorComment = generatorComment;
    }

    /**
     * Returns the {@link org.apache.batik.svggen.ErrorHandler} that
     * has been set.
     */
    final public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    /**
     * Sets the {@link org.apache.batik.svggen.Errorhandler}
     * to be used. It should not be <code>null</code>.
     */
    final protected void setErrorHandler(ErrorHandler errorHandler) {
        if (errorHandler == null)
            throw new SVGGraphics2DRuntimeException(ERR_ERROR_HANDLER_NULL);
        this.errorHandler = errorHandler;
    }
}
