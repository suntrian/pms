
dependencies {
    implementation(project(":interface"))
    implementation("org.eclipse.jgit:org.eclipse.jgit:5.10.0.202012080955-r")
    implementation("org.eclipse.jgit:org.eclipse.jgit.ssh.jsch:5.10.0.202012080955-r")
    testImplementation("org.eclipse.jgit:org.eclipse.jgit.junit:5.10.0.202012080955-r")
    testImplementation("org.eclipse.jgit:org.eclipse.jgit.junit.ssh:5.10.0.202012080955-r")
    testImplementation("org.eclipse.jgit:org.eclipse.jgit.junit.http:5.10.0.202012080955-r")
}
