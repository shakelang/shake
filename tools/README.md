# Tools

This directory contains tools / scripts that are useful for working with the project or used during ci builds.

- [`run_gradlew.py`](run_gradlew.py): A script that runs gradlew command, piping the output
  to the console and also to a file. Usage: `python run_gradlew.py <gradlew command>`.

- [`convert_to_sarif.py`](convert_to_sarif.py): A script that converts the output of the build process (the outputs
  saved by [`run_gradlew.py`](run_gradlew.py)) to the SARIF format. Usage:
  `python convert_to_sarif.py <gradlew-output-file> >> <sarif-output-file>`.
