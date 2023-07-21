#!/bin/bash

# ---------------------------------- uninstall java, gradle, and tomcat via sdkman ----------------------------------
uninstall_sdkman_tools(){
    start_log "${FUNCNAME[0]}"

    sdk uninstall java "${JAVA_VERSION}" --force || handle_error "Failed to uninstall Java"
    sdk uninstall gradle "${GRADLE_VERSION}" --force || handle_error "Failed to uninstall Gradle"
}

# ---------------------------------- Call the functions ----------------------------------
source ./shared.sh

uninstall_sdkman_tools