#!/bin/bash

# ---------------------------------- install pacakges ----------------------------------
install_packages(){
    start_log "${FUNCNAME[0]}"

    PKG="git curl zip unzip"

    sudo apt-get update -y
    sudo apt-get install ${PKG} -y
}

# ---------------------------------- install sdkman for java ----------------------------------
install_sdkman(){
    start_log "${FUNCNAME[0]}"

    curl -s "https://get.sdkman.io" | bash || handle_error "Failed to install SDKMAN"
    source "${HOME}/.sdkman/bin/sdkman-init.sh"

    sdk install java "${JAVA_VERSION}" || handle_error "Failed to install Java"

    sdk install gradle "${GRADLE_VERSION}" || handle_error "Failed to install Gradle"
}

# ---------------------------------- Call the functions ----------------------------------
source ./shared.sh

install_packages
install_sdkman

. ${HOME}/.bashrc