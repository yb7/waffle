/**
 * Scala front-end to SLF4J API.
 */
package com.wyb7.waffle.commons.util

import org.slf4j.{Logger => SLF4JLogger}

/**
 * Scala front-end to a SLF4J logger.
 */
class Logger(val logger: SLF4JLogger)
{
    /**
     * Get the name associated with this logger.
     *
     * @return the name.
     */
    def name = logger.getName

    /**
     * Determine whether trace logging is enabled.
     */
    def isTraceEnabled = logger.isTraceEnabled

    /**
     * Issue a trace logging message.
     *
     * @param msg  the message object. `toString()` is called to convert it
     *             to a loggable string.
     */
    def trace(msg: => AnyRef): Unit =
        if (isTraceEnabled) logger.trace(msg.toString)

    /**
     * Issue a trace logging message, with an exception.
     *
     * @param msg  the message object. `toString()` is called to convert it
     *             to a loggable string.
     * @param t    the exception to include with the logged message.
     */
    def trace(msg: => AnyRef, t: => Throwable): Unit =
        if (isTraceEnabled) logger.trace(msg.toString, t)

    /**
     * Determine whether debug logging is enabled.
     */
    def isDebugEnabled = logger.isDebugEnabled

    /**
     * Issue a debug logging message.
     *
     * @param msg  the message object. `toString()` is called to convert it
     *             to a loggable string.
     */
    def debug(msg: => AnyRef): Unit =
        if (isDebugEnabled) logger.debug(msg.toString)

    /**
     * Issue a debug logging message, with an exception.
     *
     * @param msg  the message object. `toString()` is called to convert it
     *             to a loggable string.
     * @param t    the exception to include with the logged message.
     */
    def debug(msg: => AnyRef, t: => Throwable): Unit =
        if (isDebugEnabled) logger.debug(msg.toString, t)

    /**
     * Determine whether trace logging is enabled.
     */
    def isErrorEnabled = logger.isErrorEnabled

    /**
     * Issue a trace logging message.
     *
     * @param msg  the message object. `toString()` is called to convert it
     *             to a loggable string.
     */
    def error(msg: => AnyRef): Unit =
        if (isErrorEnabled) logger.error(msg.toString)

    /**
     * Issue a trace logging message, with an exception.
     *
     * @param msg  the message object. `toString()` is called to convert it
     *             to a loggable string.
     * @param t    the exception to include with the logged message.
     */
    def error(msg: => AnyRef, t: => Throwable): Unit =
        if (isErrorEnabled) logger.error(msg.toString, t)

    /**
     * Determine whether trace logging is enabled.
     */
    def isInfoEnabled = logger.isInfoEnabled

    /**
     * Issue a trace logging message.
     *
     * @param msg  the message object. `toString()` is called to convert it
     *             to a loggable string.
     */
    def info(msg: => AnyRef): Unit =
        if (isInfoEnabled) logger.info(msg.toString)

    /**
     * Issue a trace logging message, with an exception.
     *
     * @param msg  the message object. `toString()` is called to convert it
     *             to a loggable string.
     * @param t    the exception to include with the logged message.
     */
    def info(msg: => AnyRef, t: => Throwable): Unit =
        if (isInfoEnabled) logger.info(msg.toString, t)

    /**
     * Determine whether trace logging is enabled.
     */
    def isWarnEnabled = logger.isWarnEnabled

    /**
     * Issue a trace logging message.
     *
     * @param msg  the message object. `toString()` is called to convert it
     *             to a loggable string.
     */
    def warn(msg: => AnyRef): Unit =
        if (isWarnEnabled) logger.warn(msg.toString)

    /**
     * Issue a trace logging message, with an exception.
     *
     * @param msg  the message object. `toString()` is called to convert it
     *             to a loggable string.
     * @param t    the exception to include with the logged message.
     */
    def warn(msg: => AnyRef, t: => Throwable): Unit =
        if (isWarnEnabled) logger.warn(msg.toString, t)
}

/**
 * Mix the `Logging` trait into a class to get:
 *
 * - Logging methods
 * - A `Logger` object, accessible via the `log` property
 *
 * Does not affect the public API of the class mixing it in.
 */
trait Logging
{
    // The logger. Instantiated the first time it's used.
    private lazy val _logger = Logger(getClass)

    /**
     * Get the `Logger` for the class that mixes this trait in. The `Logger`
     * is created the first time this method is call. The other methods (e.g.,
     * `error`, `info`, etc.) call this method to get the logger.
     *
     * @return the `Logger`
     */
    protected def logger: Logger = _logger

    /**
     * Get the name associated with this logger.
     *
     * @return the name.
     */
    protected def loggerName = logger.name

    /**
     * Determine whether trace logging is enabled.
     */
    protected def isTraceEnabled = logger.isTraceEnabled

    /**
     * Issue a trace logging message.
     *
     * @param msg  the message object. `toString()` is called to convert it
     *             to a loggable string.
     */
    protected def trace(msg: => AnyRef): Unit = logger.trace(msg)

    /**
     * Issue a trace logging message, with an exception.
     *
     * @param msg  the message object. `toString()` is called to convert it
     *             to a loggable string.
     * @param t    the exception to include with the logged message.
     */
    protected def trace(msg: => AnyRef, t: => Throwable): Unit =
        logger.trace(msg, t)

    /**
     * Determine whether debug logging is enabled.
     */
    protected def isDebugEnabled = logger.isDebugEnabled

    /**
     * Issue a debug logging message.
     *
     * @param msg  the message object. `toString()` is called to convert it
     *             to a loggable string.
     */
    protected def debug(msg: => AnyRef): Unit = logger.debug(msg)

    /**
     * Issue a debug logging message, with an exception.
     *
     * @param msg  the message object. `toString()` is called to convert it
     *             to a loggable string.
     * @param t    the exception to include with the logged message.
     */
    protected def debug(msg: => AnyRef, t: => Throwable): Unit =
        logger.debug(msg, t)

    /**
     * Determine whether trace logging is enabled.
     */
    protected def isErrorEnabled = logger.isErrorEnabled

    /**
     * Issue a trace logging message.
     *
     * @param msg  the message object. `toString()` is called to convert it
     *             to a loggable string.
     */
    protected def error(msg: => AnyRef): Unit = logger.error(msg)

    /**
     * Issue a trace logging message, with an exception.
     *
     * @param msg  the message object. `toString()` is called to convert it
     *             to a loggable string.
     * @param t    the exception to include with the logged message.
     */
    protected def error(msg: => AnyRef, t: => Throwable): Unit =
        logger.error(msg, t)

    /**
     * Determine whether trace logging is enabled.
     */
    protected def isInfoEnabled = logger.isInfoEnabled

    /**
     * Issue a trace logging message.
     *
     * @param msg  the message object. `toString()` is called to convert it
     *             to a loggable string.
     */
    protected def info(msg: => AnyRef): Unit = logger.info(msg)

    /**
     * Issue a trace logging message, with an exception.
     *
     * @param msg  the message object. `toString()` is called to convert it
     *             to a loggable string.
     * @param t    the exception to include with the logged message.
     */
    protected def info(msg: => AnyRef, t: => Throwable): Unit =
        logger.info(msg, t)

    /**
     * Determine whether trace logging is enabled.
     */
    protected def isWarnEnabled = logger.isWarnEnabled

    /**
     * Issue a trace logging message.
     *
     * @param msg  the message object. `toString()` is called to convert it
     *             to a loggable string.
     */
    protected def warn(msg: => AnyRef): Unit = logger.warn(msg)

    /**
     * Issue a trace logging message, with an exception.
     *
     * @param msg  the message object. `toString()` is called to convert it
     *             to a loggable string.
     * @param t    the exception to include with the logged message.
     */
    protected def warn(msg: => AnyRef, t: => Throwable): Unit =
        logger.warn(msg, t)
}

/**
 * A factory for retrieving an SLF4JLogger.
 */
object Logger
{
    /**
     * The name associated with the root logger.
     */
    val RootLoggerName = SLF4JLogger.ROOT_LOGGER_NAME

    /**
     * Get the logger with the specified name. Use `RootName` to get the
     * root logger.
     *
     * @param name  the logger name
     *
     * @return the `Logger`.
     */
    def apply(name: String): Logger =
        new Logger(org.slf4j.LoggerFactory.getLogger(name))

    /**
     * Get the logger for the specified class, using the class's fully
     * qualified name as the logger name.
     *
     * @param cls  the class
     *
     * @return the `Logger`.
     */
    def apply(cls: Class[_]): Logger = apply(cls.getName)

    /**
     * Get the root logger.
     *
     * @return the root logger
     */
    def rootLogger = apply(RootLoggerName)
}
