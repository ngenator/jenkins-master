import jenkins.model.*

def instance = Jenkins.getInstance()

println "--> setting master executors"
instance.setNumExecutors(0)
instance.save()
println "--> setting master executors... done"
