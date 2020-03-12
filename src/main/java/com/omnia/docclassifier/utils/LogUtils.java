/*
----------------------------------
  LogUtils Class

  Copyright 2011
  Digital Provisioners, LLC
  All Rights Reserved
----------------------------------
 */
package com.omnia.docclassifier.utils;

import org.slf4j.MDC;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class LogUtils
{
    //---------------------------------- methods

    /**
     * formats an error log message such that the name of the current class and
     * method prefixes the exception message and call stack
     * @param ex - Exception
     * @return - String
     */
    public static String formatError(Exception ex) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String[] pkg = stackTrace[2].getClassName().split("\\.");
        return pkg[pkg.length-1] + "." + stackTrace[2].getMethodName() + ": " + ex.getMessage() + "\r\n" + getStackTrace(ex);
    }

    public static String formatError(String msg, Exception ex) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String[] pkg = stackTrace[2].getClassName().split("\\.");
        return pkg[pkg.length-1] + "." + stackTrace[2].getMethodName() + ": " + msg + " - " + ex.getMessage() + "\r\n" + getStackTrace(ex);
    }

    /**
     * getStrackTrace returns a string version of a stack trace,
     * which is handy for logging.
     * @param ex - Throwable
     * @return - String
     */
    public static String getStackTrace(Throwable ex)
    {
        final int MAX_STACK             = 2048;
        final Writer result 			= new StringWriter();
        final PrintWriter printWriter 	= new PrintWriter(result);
        ex.printStackTrace(printWriter);
        String trace = result.toString();
        return (trace.length() > MAX_STACK) ? trace.substring(0, MAX_STACK) : trace;
    }

    /**
     * formats a log message such that the name of the current class and
     * method prefixes the message
     * @param msg - String
     * @return String
     */
    public static String formatMessage(String msg) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String[] pkg = stackTrace[2].getClassName().split("\\.");
        return pkg[pkg.length-1] + "." + stackTrace[2].getMethodName() + ": " + msg;
    }
    /**
     * puts a value into the logging context. It is OK to pass a null value, perhaps
     * to clear the context and output to a default log
     * @param key - String
     * @param value - Object
     */
    public static void setLogContext(String key, Object value)
    {
        MDC.put(key, (value != null) ? value.toString() : null);
    }

    //---------------------------------- constructor

    // private constructor insures class treated as
    // static library (can't be instantiated with new)
    private LogUtils()
    {}


}
