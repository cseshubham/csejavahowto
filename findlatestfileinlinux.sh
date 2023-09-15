#!/bin/bash

directory="/path/to/your/directory"
current_date=$(date +"%Y-%m-%d")
latest_file=""

# Check if the directory exists
if [ -d "$directory" ]; then
    # Use find to search for files matching the criteria
    latest_file=$(find "$directory" -type f -name "shutdown-error*.log" -newermt "$current_date" -exec ls -1t {} + | head -n 1)
    
    # Check if any matching files were found
    if [ -n "$latest_file" ]; then
        echo "Latest file modified on $current_date: $latest_file"
    else
        echo "No matching files found."
    fi
else
    echo "Directory does not exist."
fi
