import subprocess
import sys

# This script is a wrapper around gradlew that captures the output of gradlew and saves
# it to a file while also piping the output to the console. The exit code of gradlew is
# returned as the exit code of this script.
# This script is used to run gradlew in GitHub Actions workflows. The output can then
# be converted using convert_gradle_output.py to sarif format for GitHub code scanning.
#
# Usage: python tools/run_gradlew.py <arguments>
# Example: python tools/run_gradlew.py clean build


def run_gradlew(arguments):
    # Run gradlew with the provided arguments
    command = ['./gradlew'] + arguments

    try:
        # Open a file to save the output
        with open('gradle_output.txt', 'w') as output_file:
            # Run the command and capture the output
            process = subprocess.Popen(command, stdout=subprocess.PIPE, stderr=subprocess.STDOUT, universal_newlines=True)

            # Print output to console and save it to the file simultaneously
            for line in process.stdout:
                print(line, end='')
                output_file.write(line)

            # Wait for the process to finish
            process.wait()
    except Exception as e:
        if process is not None:
            process.kill()

    # Return the exit code of gradlew
    return process.returncode


def main():
    # Get command-line arguments excluding the script name
    arguments = sys.argv[1:]

    # Run gradlew with the provided arguments
    return_code = run_gradlew(arguments)

    # Return the exit code of gradlew
    sys.exit(return_code)


if __name__ == "__main__":
    main()
