import jenkins.model.*
import hudson.security.*
// import org.jenkinsci.plugins.*

def env = System.getenv()
def instance = Jenkins.getInstance()

// TODO: make these configurable
// String server = 'ldap://1.2.3.4'
// String rootDN = 'dc=foo,dc=com'
// String userSearchBase = 'cn=users,cn=accounts'
// String userSearch = ''
// String groupSearchBase = ''
// String managerDN = 'uid=serviceaccount,cn=users,cn=accounts,dc=foo,dc=com'
// String managerPassword = 'password'
// boolean inhibitInferRootDN = false

// SecurityRealm ldapRealm = new LDAPSecurityRealm(server, rootDN, userSearchBase, userSearch, groupSearchBase, managerDN, managerPassword, inhibitInferRootDN)
// instance.setSecurityRealm(ldapRealm)

println "--> setting security realm"
def hudsonRealm = new HudsonPrivateSecurityRealm(false, false, null)
hudsonRealm.createAccount(env['JENKINS_USER'], env['JENKINS_PASS'])
instance.setSecurityRealm(hudsonRealm)
instance.save()
println "--> setting security realm... done"

println "--> setting authorization strategy"
def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
instance.setAuthorizationStrategy(strategy)
instance.save()
println "--> setting authorization strategy... done"
