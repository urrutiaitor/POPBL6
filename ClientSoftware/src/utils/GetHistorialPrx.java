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

public interface GetHistorialPrx extends Ice.ObjectPrx
{
    public boolean comprobarUsuario(String usuario, String contrasena);

    public boolean comprobarUsuario(String usuario, String contrasena, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_comprobarUsuario(String usuario, String contrasena);

    public Ice.AsyncResult begin_comprobarUsuario(String usuario, String contrasena, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_comprobarUsuario(String usuario, String contrasena, Ice.Callback __cb);

    public Ice.AsyncResult begin_comprobarUsuario(String usuario, String contrasena, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_comprobarUsuario(String usuario, String contrasena, Callback_GetHistorial_comprobarUsuario __cb);

    public Ice.AsyncResult begin_comprobarUsuario(String usuario, String contrasena, java.util.Map<String, String> __ctx, Callback_GetHistorial_comprobarUsuario __cb);

    public Ice.AsyncResult begin_comprobarUsuario(String usuario, 
                                                  String contrasena, 
                                                  IceInternal.Functional_BoolCallback __responseCb, 
                                                  IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_comprobarUsuario(String usuario, 
                                                  String contrasena, 
                                                  IceInternal.Functional_BoolCallback __responseCb, 
                                                  IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                  IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_comprobarUsuario(String usuario, 
                                                  String contrasena, 
                                                  java.util.Map<String, String> __ctx, 
                                                  IceInternal.Functional_BoolCallback __responseCb, 
                                                  IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_comprobarUsuario(String usuario, 
                                                  String contrasena, 
                                                  java.util.Map<String, String> __ctx, 
                                                  IceInternal.Functional_BoolCallback __responseCb, 
                                                  IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                  IceInternal.Functional_BoolCallback __sentCb);

    public boolean end_comprobarUsuario(Ice.AsyncResult __result);

    public void getProx(String usuario, String contrasena, StringVectorHolder vector);

    public void getProx(String usuario, String contrasena, StringVectorHolder vector, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getProx(String usuario, String contrasena);

    public Ice.AsyncResult begin_getProx(String usuario, String contrasena, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getProx(String usuario, String contrasena, Ice.Callback __cb);

    public Ice.AsyncResult begin_getProx(String usuario, String contrasena, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_getProx(String usuario, String contrasena, Callback_GetHistorial_getProx __cb);

    public Ice.AsyncResult begin_getProx(String usuario, String contrasena, java.util.Map<String, String> __ctx, Callback_GetHistorial_getProx __cb);

    public Ice.AsyncResult begin_getProx(String usuario, 
                                         String contrasena, 
                                         IceInternal.Functional_GenericCallback1<String[]> __responseCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_getProx(String usuario, 
                                         String contrasena, 
                                         IceInternal.Functional_GenericCallback1<String[]> __responseCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                         IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_getProx(String usuario, 
                                         String contrasena, 
                                         java.util.Map<String, String> __ctx, 
                                         IceInternal.Functional_GenericCallback1<String[]> __responseCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_getProx(String usuario, 
                                         String contrasena, 
                                         java.util.Map<String, String> __ctx, 
                                         IceInternal.Functional_GenericCallback1<String[]> __responseCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                         IceInternal.Functional_BoolCallback __sentCb);

    public void end_getProx(StringVectorHolder vector, Ice.AsyncResult __result);

    public void getTemp(String usuario, String contrasena, StringVectorHolder vector);

    public void getTemp(String usuario, String contrasena, StringVectorHolder vector, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getTemp(String usuario, String contrasena);

    public Ice.AsyncResult begin_getTemp(String usuario, String contrasena, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getTemp(String usuario, String contrasena, Ice.Callback __cb);

    public Ice.AsyncResult begin_getTemp(String usuario, String contrasena, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_getTemp(String usuario, String contrasena, Callback_GetHistorial_getTemp __cb);

    public Ice.AsyncResult begin_getTemp(String usuario, String contrasena, java.util.Map<String, String> __ctx, Callback_GetHistorial_getTemp __cb);

    public Ice.AsyncResult begin_getTemp(String usuario, 
                                         String contrasena, 
                                         IceInternal.Functional_GenericCallback1<String[]> __responseCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_getTemp(String usuario, 
                                         String contrasena, 
                                         IceInternal.Functional_GenericCallback1<String[]> __responseCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                         IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_getTemp(String usuario, 
                                         String contrasena, 
                                         java.util.Map<String, String> __ctx, 
                                         IceInternal.Functional_GenericCallback1<String[]> __responseCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_getTemp(String usuario, 
                                         String contrasena, 
                                         java.util.Map<String, String> __ctx, 
                                         IceInternal.Functional_GenericCallback1<String[]> __responseCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                         IceInternal.Functional_BoolCallback __sentCb);

    public void end_getTemp(StringVectorHolder vector, Ice.AsyncResult __result);
}