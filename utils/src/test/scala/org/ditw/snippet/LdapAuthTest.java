package org.ditw.snippet;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;


public class LdapAuthTest {
    public static void main(String[] args) {
        LdapAuthTest ldapContxCrtn = new LdapAuthTest();
        LdapContext ctx = ldapContxCrtn.getLdapContext();
        System.out.println("context created!");
    }
    public LdapContext getLdapContext(){
        LdapContext ctx = null;
        try{
            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY,  "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.SECURITY_AUTHENTICATION, "Simple");
            //it can be <domain\\userid> something that you use for windows login
            //it can also be
            env.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
            env.put(Context.SECURITY_CREDENTIALS, "123");
            //in following property we specify ldap protocol and connection url.
            //generally the port is 389
            env.put(Context.PROVIDER_URL, "ldap://localhost:32768");
            ctx = new InitialLdapContext(env, null);
            System.out.println("Connection Successful.");
        }catch(NamingException nex){
            System.out.println("LDAP Connection: FAILED");
            nex.printStackTrace();
        }
        return ctx;
    }

}