// **********************************************************************
//
// Copyright (c) 2003-2015 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.6.1
//
// <auto-generated>
//
// Generated from file `GetHistorial.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package utils;

public final class GetHistorialPrxHelper extends Ice.ObjectPrxHelperBase implements GetHistorialPrx
{
    private static final String __comprobarUsuario_name = "comprobarUsuario";

    public boolean comprobarUsuario(String usuario, String contrasena)
    {
        return comprobarUsuario(usuario, contrasena, null, false);
    }

    public boolean comprobarUsuario(String usuario, String contrasena, java.util.Map<String, String> __ctx)
    {
        return comprobarUsuario(usuario, contrasena, __ctx, true);
    }

    private boolean comprobarUsuario(String usuario, String contrasena, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        __checkTwowayOnly(__comprobarUsuario_name);
        return end_comprobarUsuario(begin_comprobarUsuario(usuario, contrasena, __ctx, __explicitCtx, true, null));
    }

    public Ice.AsyncResult begin_comprobarUsuario(String usuario, String contrasena)
    {
        return begin_comprobarUsuario(usuario, contrasena, null, false, false, null);
    }

    public Ice.AsyncResult begin_comprobarUsuario(String usuario, String contrasena, java.util.Map<String, String> __ctx)
    {
        return begin_comprobarUsuario(usuario, contrasena, __ctx, true, false, null);
    }

    public Ice.AsyncResult begin_comprobarUsuario(String usuario, String contrasena, Ice.Callback __cb)
    {
        return begin_comprobarUsuario(usuario, contrasena, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_comprobarUsuario(String usuario, String contrasena, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_comprobarUsuario(usuario, contrasena, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_comprobarUsuario(String usuario, String contrasena, Callback_GetHistorial_comprobarUsuario __cb)
    {
        return begin_comprobarUsuario(usuario, contrasena, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_comprobarUsuario(String usuario, String contrasena, java.util.Map<String, String> __ctx, Callback_GetHistorial_comprobarUsuario __cb)
    {
        return begin_comprobarUsuario(usuario, contrasena, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_comprobarUsuario(String usuario, 
                                                  String contrasena, 
                                                  IceInternal.Functional_BoolCallback __responseCb, 
                                                  IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_comprobarUsuario(usuario, contrasena, null, false, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_comprobarUsuario(String usuario, 
                                                  String contrasena, 
                                                  IceInternal.Functional_BoolCallback __responseCb, 
                                                  IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                  IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_comprobarUsuario(usuario, contrasena, null, false, false, __responseCb, __exceptionCb, __sentCb);
    }

    public Ice.AsyncResult begin_comprobarUsuario(String usuario, 
                                                  String contrasena, 
                                                  java.util.Map<String, String> __ctx, 
                                                  IceInternal.Functional_BoolCallback __responseCb, 
                                                  IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_comprobarUsuario(usuario, contrasena, __ctx, true, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_comprobarUsuario(String usuario, 
                                                  String contrasena, 
                                                  java.util.Map<String, String> __ctx, 
                                                  IceInternal.Functional_BoolCallback __responseCb, 
                                                  IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                  IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_comprobarUsuario(usuario, contrasena, __ctx, true, false, __responseCb, __exceptionCb, __sentCb);
    }

    private Ice.AsyncResult begin_comprobarUsuario(String usuario, 
                                                   String contrasena, 
                                                   java.util.Map<String, String> __ctx, 
                                                   boolean __explicitCtx, 
                                                   boolean __synchronous, 
                                                   IceInternal.Functional_BoolCallback __responseCb, 
                                                   IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                   IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_comprobarUsuario(usuario, contrasena, __ctx, __explicitCtx, __synchronous, 
                                      new IceInternal.Functional_TwowayCallbackBool(__responseCb, __exceptionCb, __sentCb)
                                          {
                                              public final void __completed(Ice.AsyncResult __result)
                                              {
                                                  GetHistorialPrxHelper.__comprobarUsuario_completed(this, __result);
                                              }
                                          });
    }

    private Ice.AsyncResult begin_comprobarUsuario(String usuario, 
                                                   String contrasena, 
                                                   java.util.Map<String, String> __ctx, 
                                                   boolean __explicitCtx, 
                                                   boolean __synchronous, 
                                                   IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__comprobarUsuario_name);
        IceInternal.OutgoingAsync __result = getOutgoingAsync(__comprobarUsuario_name, __cb);
        try
        {
            __result.prepare(__comprobarUsuario_name, Ice.OperationMode.Normal, __ctx, __explicitCtx, __synchronous);
            IceInternal.BasicStream __os = __result.startWriteParams(Ice.FormatType.DefaultFormat);
            __os.writeString(usuario);
            __os.writeString(contrasena);
            __result.endWriteParams();
            __result.invoke();
        }
        catch(Ice.Exception __ex)
        {
            __result.abort(__ex);
        }
        return __result;
    }

    public boolean end_comprobarUsuario(Ice.AsyncResult __iresult)
    {
        IceInternal.OutgoingAsync __result = IceInternal.OutgoingAsync.check(__iresult, this, __comprobarUsuario_name);
        try
        {
            if(!__result.__wait())
            {
                try
                {
                    __result.throwUserException();
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
                }
            }
            IceInternal.BasicStream __is = __result.startReadParams();
            boolean __ret;
            __ret = __is.readBool();
            __result.endReadParams();
            return __ret;
        }
        finally
        {
            if(__result != null)
            {
                __result.cacheMessageBuffers();
            }
        }
    }

    static public void __comprobarUsuario_completed(Ice.TwowayCallbackBool __cb, Ice.AsyncResult __result)
    {
        utils.GetHistorialPrx __proxy = (utils.GetHistorialPrx)__result.getProxy();
        boolean __ret = false;
        try
        {
            __ret = __proxy.end_comprobarUsuario(__result);
        }
        catch(Ice.LocalException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        catch(Ice.SystemException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        __cb.response(__ret);
    }

    private static final String __getProx_name = "getProx";

    public String[] getProx(String usuario, String contrasena)
    {
        return getProx(usuario, contrasena, null, false);
    }

    public String[] getProx(String usuario, String contrasena, java.util.Map<String, String> __ctx)
    {
        return getProx(usuario, contrasena, __ctx, true);
    }

    private String[] getProx(String usuario, String contrasena, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        __checkTwowayOnly(__getProx_name);
        return end_getProx(begin_getProx(usuario, contrasena, __ctx, __explicitCtx, true, null));
    }

    public Ice.AsyncResult begin_getProx(String usuario, String contrasena)
    {
        return begin_getProx(usuario, contrasena, null, false, false, null);
    }

    public Ice.AsyncResult begin_getProx(String usuario, String contrasena, java.util.Map<String, String> __ctx)
    {
        return begin_getProx(usuario, contrasena, __ctx, true, false, null);
    }

    public Ice.AsyncResult begin_getProx(String usuario, String contrasena, Ice.Callback __cb)
    {
        return begin_getProx(usuario, contrasena, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_getProx(String usuario, String contrasena, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_getProx(usuario, contrasena, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_getProx(String usuario, String contrasena, Callback_GetHistorial_getProx __cb)
    {
        return begin_getProx(usuario, contrasena, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_getProx(String usuario, String contrasena, java.util.Map<String, String> __ctx, Callback_GetHistorial_getProx __cb)
    {
        return begin_getProx(usuario, contrasena, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_getProx(String usuario, 
                                         String contrasena, 
                                         IceInternal.Functional_GenericCallback1<String[]> __responseCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_getProx(usuario, contrasena, null, false, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_getProx(String usuario, 
                                         String contrasena, 
                                         IceInternal.Functional_GenericCallback1<String[]> __responseCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                         IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_getProx(usuario, contrasena, null, false, false, __responseCb, __exceptionCb, __sentCb);
    }

    public Ice.AsyncResult begin_getProx(String usuario, 
                                         String contrasena, 
                                         java.util.Map<String, String> __ctx, 
                                         IceInternal.Functional_GenericCallback1<String[]> __responseCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_getProx(usuario, contrasena, __ctx, true, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_getProx(String usuario, 
                                         String contrasena, 
                                         java.util.Map<String, String> __ctx, 
                                         IceInternal.Functional_GenericCallback1<String[]> __responseCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                         IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_getProx(usuario, contrasena, __ctx, true, false, __responseCb, __exceptionCb, __sentCb);
    }

    private Ice.AsyncResult begin_getProx(String usuario, 
                                          String contrasena, 
                                          java.util.Map<String, String> __ctx, 
                                          boolean __explicitCtx, 
                                          boolean __synchronous, 
                                          IceInternal.Functional_GenericCallback1<String[]> __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                          IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_getProx(usuario, contrasena, __ctx, __explicitCtx, __synchronous, 
                             new IceInternal.Functional_TwowayCallbackArg1<String[]>(__responseCb, __exceptionCb, __sentCb)
                                 {
                                     public final void __completed(Ice.AsyncResult __result)
                                     {
                                         GetHistorialPrxHelper.__getProx_completed(this, __result);
                                     }
                                 });
    }

    private Ice.AsyncResult begin_getProx(String usuario, 
                                          String contrasena, 
                                          java.util.Map<String, String> __ctx, 
                                          boolean __explicitCtx, 
                                          boolean __synchronous, 
                                          IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__getProx_name);
        IceInternal.OutgoingAsync __result = getOutgoingAsync(__getProx_name, __cb);
        try
        {
            __result.prepare(__getProx_name, Ice.OperationMode.Normal, __ctx, __explicitCtx, __synchronous);
            IceInternal.BasicStream __os = __result.startWriteParams(Ice.FormatType.DefaultFormat);
            __os.writeString(usuario);
            __os.writeString(contrasena);
            __result.endWriteParams();
            __result.invoke();
        }
        catch(Ice.Exception __ex)
        {
            __result.abort(__ex);
        }
        return __result;
    }

    public String[] end_getProx(Ice.AsyncResult __iresult)
    {
        IceInternal.OutgoingAsync __result = IceInternal.OutgoingAsync.check(__iresult, this, __getProx_name);
        try
        {
            if(!__result.__wait())
            {
                try
                {
                    __result.throwUserException();
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
                }
            }
            IceInternal.BasicStream __is = __result.startReadParams();
            String[] __ret;
            __ret = StringVectorHelper.read(__is);
            __result.endReadParams();
            return __ret;
        }
        finally
        {
            if(__result != null)
            {
                __result.cacheMessageBuffers();
            }
        }
    }

    static public void __getProx_completed(Ice.TwowayCallbackArg1<String[]> __cb, Ice.AsyncResult __result)
    {
        utils.GetHistorialPrx __proxy = (utils.GetHistorialPrx)__result.getProxy();
        String[] __ret = null;
        try
        {
            __ret = __proxy.end_getProx(__result);
        }
        catch(Ice.LocalException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        catch(Ice.SystemException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        __cb.response(__ret);
    }

    private static final String __getTemp_name = "getTemp";

    public String[] getTemp(String usuario, String contrasena)
    {
        return getTemp(usuario, contrasena, null, false);
    }

    public String[] getTemp(String usuario, String contrasena, java.util.Map<String, String> __ctx)
    {
        return getTemp(usuario, contrasena, __ctx, true);
    }

    private String[] getTemp(String usuario, String contrasena, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        __checkTwowayOnly(__getTemp_name);
        return end_getTemp(begin_getTemp(usuario, contrasena, __ctx, __explicitCtx, true, null));
    }

    public Ice.AsyncResult begin_getTemp(String usuario, String contrasena)
    {
        return begin_getTemp(usuario, contrasena, null, false, false, null);
    }

    public Ice.AsyncResult begin_getTemp(String usuario, String contrasena, java.util.Map<String, String> __ctx)
    {
        return begin_getTemp(usuario, contrasena, __ctx, true, false, null);
    }

    public Ice.AsyncResult begin_getTemp(String usuario, String contrasena, Ice.Callback __cb)
    {
        return begin_getTemp(usuario, contrasena, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_getTemp(String usuario, String contrasena, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_getTemp(usuario, contrasena, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_getTemp(String usuario, String contrasena, Callback_GetHistorial_getTemp __cb)
    {
        return begin_getTemp(usuario, contrasena, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_getTemp(String usuario, String contrasena, java.util.Map<String, String> __ctx, Callback_GetHistorial_getTemp __cb)
    {
        return begin_getTemp(usuario, contrasena, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_getTemp(String usuario, 
                                         String contrasena, 
                                         IceInternal.Functional_GenericCallback1<String[]> __responseCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_getTemp(usuario, contrasena, null, false, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_getTemp(String usuario, 
                                         String contrasena, 
                                         IceInternal.Functional_GenericCallback1<String[]> __responseCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                         IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_getTemp(usuario, contrasena, null, false, false, __responseCb, __exceptionCb, __sentCb);
    }

    public Ice.AsyncResult begin_getTemp(String usuario, 
                                         String contrasena, 
                                         java.util.Map<String, String> __ctx, 
                                         IceInternal.Functional_GenericCallback1<String[]> __responseCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_getTemp(usuario, contrasena, __ctx, true, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_getTemp(String usuario, 
                                         String contrasena, 
                                         java.util.Map<String, String> __ctx, 
                                         IceInternal.Functional_GenericCallback1<String[]> __responseCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                         IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_getTemp(usuario, contrasena, __ctx, true, false, __responseCb, __exceptionCb, __sentCb);
    }

    private Ice.AsyncResult begin_getTemp(String usuario, 
                                          String contrasena, 
                                          java.util.Map<String, String> __ctx, 
                                          boolean __explicitCtx, 
                                          boolean __synchronous, 
                                          IceInternal.Functional_GenericCallback1<String[]> __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                          IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_getTemp(usuario, contrasena, __ctx, __explicitCtx, __synchronous, 
                             new IceInternal.Functional_TwowayCallbackArg1<String[]>(__responseCb, __exceptionCb, __sentCb)
                                 {
                                     public final void __completed(Ice.AsyncResult __result)
                                     {
                                         GetHistorialPrxHelper.__getTemp_completed(this, __result);
                                     }
                                 });
    }

    private Ice.AsyncResult begin_getTemp(String usuario, 
                                          String contrasena, 
                                          java.util.Map<String, String> __ctx, 
                                          boolean __explicitCtx, 
                                          boolean __synchronous, 
                                          IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__getTemp_name);
        IceInternal.OutgoingAsync __result = getOutgoingAsync(__getTemp_name, __cb);
        try
        {
            __result.prepare(__getTemp_name, Ice.OperationMode.Normal, __ctx, __explicitCtx, __synchronous);
            IceInternal.BasicStream __os = __result.startWriteParams(Ice.FormatType.DefaultFormat);
            __os.writeString(usuario);
            __os.writeString(contrasena);
            __result.endWriteParams();
            __result.invoke();
        }
        catch(Ice.Exception __ex)
        {
            __result.abort(__ex);
        }
        return __result;
    }

    public String[] end_getTemp(Ice.AsyncResult __iresult)
    {
        IceInternal.OutgoingAsync __result = IceInternal.OutgoingAsync.check(__iresult, this, __getTemp_name);
        try
        {
            if(!__result.__wait())
            {
                try
                {
                    __result.throwUserException();
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
                }
            }
            IceInternal.BasicStream __is = __result.startReadParams();
            String[] __ret;
            __ret = StringVectorHelper.read(__is);
            __result.endReadParams();
            return __ret;
        }
        finally
        {
            if(__result != null)
            {
                __result.cacheMessageBuffers();
            }
        }
    }

    static public void __getTemp_completed(Ice.TwowayCallbackArg1<String[]> __cb, Ice.AsyncResult __result)
    {
        utils.GetHistorialPrx __proxy = (utils.GetHistorialPrx)__result.getProxy();
        String[] __ret = null;
        try
        {
            __ret = __proxy.end_getTemp(__result);
        }
        catch(Ice.LocalException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        catch(Ice.SystemException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        __cb.response(__ret);
    }

    public static GetHistorialPrx checkedCast(Ice.ObjectPrx __obj)
    {
        return checkedCastImpl(__obj, ice_staticId(), GetHistorialPrx.class, GetHistorialPrxHelper.class);
    }

    public static GetHistorialPrx checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        return checkedCastImpl(__obj, __ctx, ice_staticId(), GetHistorialPrx.class, GetHistorialPrxHelper.class);
    }

    public static GetHistorialPrx checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        return checkedCastImpl(__obj, __facet, ice_staticId(), GetHistorialPrx.class, GetHistorialPrxHelper.class);
    }

    public static GetHistorialPrx checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        return checkedCastImpl(__obj, __facet, __ctx, ice_staticId(), GetHistorialPrx.class, GetHistorialPrxHelper.class);
    }

    public static GetHistorialPrx uncheckedCast(Ice.ObjectPrx __obj)
    {
        return uncheckedCastImpl(__obj, GetHistorialPrx.class, GetHistorialPrxHelper.class);
    }

    public static GetHistorialPrx uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        return uncheckedCastImpl(__obj, __facet, GetHistorialPrx.class, GetHistorialPrxHelper.class);
    }

    public static final String[] __ids =
    {
        "::Ice::Object",
        "::utils::GetHistorial"
    };

    public static String ice_staticId()
    {
        return __ids[1];
    }

    public static void __write(IceInternal.BasicStream __os, GetHistorialPrx v)
    {
        __os.writeProxy(v);
    }

    public static GetHistorialPrx __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            GetHistorialPrxHelper result = new GetHistorialPrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }

    public static final long serialVersionUID = 0L;
}
