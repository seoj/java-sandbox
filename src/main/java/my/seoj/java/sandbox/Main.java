package my.seoj.java.sandbox;

import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.CodeSource;
import java.security.Permissions;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.security.cert.Certificate;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        CodeSource codesource = new CodeSource(null, (Certificate[]) null);
        Permissions permissions = new Permissions();
        ProtectionDomain protectionDomain = new ProtectionDomain(codesource, permissions);
        ProtectionDomain[] protectionDomains = {protectionDomain};
        AccessControlContext accessControlContext = new AccessControlContext(protectionDomains);
        AccessController.doPrivileged(new PrivilegedAction<Void>()
        {
            @Override
            public Void run()
            {
                return null;
            }
        }, accessControlContext);
    }
}
