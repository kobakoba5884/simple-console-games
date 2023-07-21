#!/bin/bash

JAVA_VERSION=17.0.7-amzn
GRADLE_VERSION=7.3

# ---------------------------------- Function to handle errors ----------------------------------
handle_error() {
    echo "Error: $1" >&2
    return
}

# ---------------------------------- Function to log the start of an operation ----------------------------------
start_log(){
    echo "------------------------ Start: $1 ------------------------"
}

# ---------------------------------- Check if command is available ----------------------------------
command_exists() {
    command -v "$1" >/dev/null 2>&1
}