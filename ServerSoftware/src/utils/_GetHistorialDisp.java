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

public abstract class _GetHistorialDisp extends Ice.ObjectImpl implements GetHistorial
{
    protected void
    ice_copyStateFrom(Ice.Object __obj)
        throws java.lang.CloneNotSupportedException
    {
        throw new java.lang.CloneNotSupportedException();
    }

    public static final String[] __ids =
    {
        "::Ice::Object",
        "::utils::GetHistorial"
    };

    public boolean ice_isA(String s)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public boolean ice_isA(String s, Ice.Current __current)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public String[] ice_ids()
    {
        return __ids;
    }

    public String[] ice_ids(Ice.Current __current)
    {
        return __ids;
    }

    public String ice_id()
    {
        return __ids[1];
    }

    public String ice_id(Ice.Current __current)
    {
        return __ids[1];
    }

    public static String ice_staticId()
    {
        return __ids[1];
    }

    public final boolean comprobarUsuario(String usuario, String contrasena)
    {
        return comprobarUsuario(usuario, contrasena, null);
    }

    public final void getProx(String usuario, String contrasena, StringVectorHolder vector)
    {
        getProx(usuario, contrasena, vector, null);
    }

    public final void getTemp(String usuario, String contrasena, StringVectorHolder vector)
    {
        getTemp(usuario, contrasena, vector, null);
    }

    public static Ice.DispatchStatus ___comprobarUsuario(GetHistorial __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        String usuario;
        String contrasena;
        usuario = __is.readString();
        contrasena = __is.readString();
        __inS.endReadParams();
        boolean __ret = __obj.comprobarUsuario(usuario, contrasena, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        __os.writeBool(__ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___getProx(GetHistorial __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        String usuario;
        String contrasena;
        usuario = __is.readString();
        contrasena = __is.readString();
        __inS.endReadParams();
        StringVectorHolder vector = new StringVectorHolder();
        __obj.getProx(usuario, contrasena, vector, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        StringVectorHelper.write(__os, vector.value);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___getTemp(GetHistorial __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        String usuario;
        String contrasena;
        usuario = __is.readString();
        contrasena = __is.readString();
        __inS.endReadParams();
        StringVectorHolder vector = new StringVectorHolder();
        __obj.getTemp(usuario, contrasena, vector, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        StringVectorHelper.write(__os, vector.value);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    private final static String[] __all =
    {
        "comprobarUsuario",
        "getProx",
        "getTemp",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping"
    };

    public Ice.DispatchStatus __dispatch(IceInternal.Incoming in, Ice.Current __current)
    {
        int pos = java.util.Arrays.binarySearch(__all, __current.operation);
        if(pos < 0)
        {
            throw new Ice.OperationNotExistException(__current.id, __current.facet, __current.operation);
        }

        switch(pos)
        {
            case 0:
            {
                return ___comprobarUsuario(this, in, __current);
            }
            case 1:
            {
                return ___getProx(this, in, __current);
            }
            case 2:
            {
                return ___getTemp(this, in, __current);
            }
            case 3:
            {
                return ___ice_id(this, in, __current);
            }
            case 4:
            {
                return ___ice_ids(this, in, __current);
            }
            case 5:
            {
                return ___ice_isA(this, in, __current);
            }
            case 6:
            {
                return ___ice_ping(this, in, __current);
            }
        }

        assert(false);
        throw new Ice.OperationNotExistException(__current.id, __current.facet, __current.operation);
    }

    protected void __writeImpl(IceInternal.BasicStream __os)
    {
        __os.startWriteSlice(ice_staticId(), -1, true);
        __os.endWriteSlice();
    }

    protected void __readImpl(IceInternal.BasicStream __is)
    {
        __is.startReadSlice();
        __is.endReadSlice();
    }

    public static final long serialVersionUID = 0L;
}