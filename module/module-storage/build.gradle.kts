dependencies {

    compileOnly("com.amazonaws:aws-java-sdk-s3:1.11.863")
    compileOnly("com.qiniu:qiniu-java-sdk:7.3.0")
    compileOnly("org.apache.hadoop:hadoop-client:3.3.0") {
        isTransitive = true
    }
    compileOnly(group = "commons-net", name = "commons-net", version = "3.8.0")
    compileOnly("com.jcraft:jsch:0.1.55")


    testImplementation("org.apache.ftpserver:ftpserver-core:1.1.1")
}

configurations {
    testImplementation {
        extendsFrom(compileOnly.get())
    }
}