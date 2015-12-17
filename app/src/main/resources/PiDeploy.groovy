host = pom.properties.host
usr = pom.properties.user
psw = pom.properties.password

verbose = false

buildDir = project.build.directory
deployDir = "~/jaumie"
jar = "${project.artifactId}-${project.version}-jar-with-dependencies.jar"
file = "$buildDir\\$jar"

// Suppress output ant
if (verbose == false) {
    ant.project.buildListeners.firstElement().messageOutputLevel = 0
}

log.info("Deploying to pi....")

log.info("Stopping JVM.")
ant.sshexec(trust: true,
        host: host,
        username: usr,
        password: psw,
        command: "pkill -f 'java -jar'",
        verbose: verbose,
        failonerror: false

)

log.info("Cleanup.")
ant.sshexec(trust: true,
        host: host,
        username: usr,
        password: psw,
        command: "rm -r $deployDir/*"
)

log.info("Deploy jar: $file to $deployDir")
ant.scp(trust: true,
        file: file,
        todir: "$usr@$host:$deployDir",
        password: psw,
        verbose: verbose
)

log.info("Starting JVM.")
ant.sshexec(trust: true,
        host: host,
        username: usr,
        password: psw,
        command: "java -jar $deployDir/$jar > /dev/null 2>&1 &"
)

log.info("Deployment done!")
