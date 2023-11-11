#!/bin/sh

mkdir -p ./build/reports/ktlint/groups

files=($(ls -1 ./build/reports/ktlint/all/*.sarif))
group_number=1
group_size=20
group_amount=$(((${#files[@]} + $group_size - 1) / $group_size))

# Step 1: Split Into Groups
echo "Splitting ${#files[@]} files into $group_amount groups of $group_size files each"

for ((i=0; i<${#files[@]}; i+=group_size)); do
  group_files=("${files[@]:i:group_size}")
  group_folder="./build/reports/ktlint/groups/group${group_number}"
  mkdir -p "$group_folder"
  cp "${group_files[@]}" "$group_folder"
  ((group_number++))
done

# Step 2: Merge Each Group
mkdir -p ./build/reports/ktlint/merged

for group in ./build/reports/ktlint/groups/*; do
  group_number=$(basename "$group")
  group_files=($(ls -1 "$group"/*.sarif))
  merged_file="${group_number}.sarif"
  merged_folder="./build/reports/ktlint/merged/"
  echo "Merging ${#group_files[@]} files into $merged_file"
  npx @microsoft/sarif-multitool merge "${group_files[@]}" --output-directory="$merged_folder" --output-file="$merged_file"
done