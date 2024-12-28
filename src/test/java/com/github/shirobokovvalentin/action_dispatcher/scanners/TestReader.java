package com.github.shirobokovvalentin.action_dispatcher.scanners;

import com.github.shirobokovvalentin.action_dispatcher.readers.ParamReader;

import javax.servlet.http.HttpServletRequest;

public class TestReader  implements ParamReader
{

    private static final TestReader instance = new TestReader();

    // ------------------------------------------------------------

    public static TestReader getInstance()
    {
        return instance;
    }

    // ------------------------------------------------------------

    public String read(HttpServletRequest request)
    {
        return "testValue";
    }

    public void setDefaultValue(String defaultValue)
    {
        //NOP
    }

    public String getParamName()
    {
        return "Header params";
    }

}