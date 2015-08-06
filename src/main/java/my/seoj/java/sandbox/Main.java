package my.seoj.java.sandbox;

import java.io.FileInputStream;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.CodeSource;
import java.security.Permissions;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.security.cert.Certificate;

/**
 * This main class should be run with the JVM arguments.
 * -Djava.security.manager -Djava.security.policy=my.java.policy
 * 
 * The my.java.policy file is located at the root of this project.
 */
public class Main
{
    public static void main(String[] args) throws Exception
    {
        /*
         * A security manager will automatically be set by the JVM if you ran this main with the security manager arguments.
         * This if statement ensures that you have done this, just for sanity check. (personal experience here)
         */
        if (System.getSecurityManager() == null)
        {
            throw new IllegalArgumentException("No security manager configured. Did you start the JVM with -Djava.security.manager -Djava.security.policy ?");
        }

        /*
         * Add list of permissions here.
         * Any permission you haven't added is assumed to be denied.
         * You can find the full list of permissions here:
         * http://docs.oracle.com/javase/7/docs/technotes/guides/security/permissions.html
         */
        Permissions permissions = new Permissions();
        // permissions.add(new AllPermission()); // All permissions
        // permissions.add(new FilePermission("test/-", "read")); // Read permissions to all files under directory "test"
        // permissions.add(new RuntimePermission("exitVM")); // Allow code to execute System.exit()

        /*
         * We have some boiler plate code to set up sandbox execution
         */

        // You can artificially sign your code by defining a source. We are not doing that here.
        CodeSource codesource = new CodeSource(null, (Certificate[]) null);

        ProtectionDomain protectionDomain = new ProtectionDomain(codesource, permissions);
        ProtectionDomain[] protectionDomains = {protectionDomain};
        AccessControlContext accessControlContext = new AccessControlContext(protectionDomains);

        // Execute the sandboxed code
        AccessController.doPrivileged(new PrivilegedAction<Void>()
        {
            @Override
            public Void run()
            {
                /*
                 * Any code in here will now be running under the set of permissions granted above.
                 * According to java documentations for the doPrivileged() method, the final set of permissions is the intersection between the system-wide
                 * permissions and the sandboxed permissions.
                 * This means if the system doesn't have a permissions, neither can the sandbox code.
                 */
                try
                {
                    new FileInputStream("test");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                return null;
            }
        }, accessControlContext);
    }
}
