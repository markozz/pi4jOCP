host = pom.properties.host
usr = pom.properties.user
psw = pom.properties.password

buildDir = project.build.directory
deployDir = "~/jaumie"
file = "$buildDir\\${project.artifactId}-${project.version}-jar-with-dependencies.jar"


log.info("Deploying to pi....")

log.info("Stopping JVM.")
ant.sshexec(trust: true,
        host: host,
        username: usr,
        password: psw,
        command: "pkill -f 'java -jar'",
        verbose: true,
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
        verbose: true
)

log.info("Starting JVM.")
ant.sshexec(trust: true,
        host: host,
        username: usr,
        password: psw,
        command: "java -jar $deployDir/$file > /dev/null 2>&1 &"
)

log.info("Deployment done!")
