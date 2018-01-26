#!/usr/bin/env bash

# #############################################################################
# A batch file to download and execute MCP, rearrange the generated code and
# setup the Minecraft module for Vibrant
#
# The MCP version can be changed by setting $MCP_RL to a different release
# But in that case, the installed libraries must be updated as well
#
# Author: Cydhra
# #############################################################################


# ################# CONSTANT POOL #################
MCP_RL='http://www.modcoderpack.com/files/mcp918.zip'
MC_VER='1.8.8'
# ################# END OF CONSTANT POOL #################

echo 'Downloading MCP...'
rm -rf tmp
mkdir tmp
cd tmp
wget $MCP_RL -O 'mcp.zip'
echo

echo 'Unzipping MCP...'
unzip 'mcp.zip'
rm 'mcp.zip'
echo

echo 'Configuring MCP...'
cd ..
cp mcp.conf tmp/conf/mcp.conf
cd tmp

echo 'Executing MCP...'
sh decompile.sh
cd ..
echo

echo 'Copying Sources...'
mkdir -p src/main/java
cp --parents tmp/src/minecraft/* src/main/java
echo

echo 'Rearrange...'
mv src/main/java/Start.java src/main/java/net/minecraft/Start.java
echo

echo 'Copying Libraries...'
mkdir lib
cp --parents tmp/jars/libraries/com/mojang/authlib/1.5.21/* lib
cp --parents tmp/jars/libraries/com/paulscode/codecjorbis/20101023/* lib
cp --parents tmp/jars/libraries/com/paulscode/codecwav\20101023/codecwav-20101023.jar lib
cp --parents tmp/jars/libraries/com/paulscode/libraryjavasound/20101123/* lib
cp --parents tmp/jars/libraries/com/paulscode/librarylwjglopenal/20100824/* lib
cp --parents tmp/jars/libraries/com/paulscode/soundsystem/20120107/* lib
cp --parents tmp/jars/libraries/oshi-project/oshi-core/1.1/* lib
cp --parents tmp/jars/libraries/tv/twitch/* lib

echo

echo Copying workspace...
mkdir workspace
cp --parents tmp/jars/* workspace
echo

echo Cleaning Up...
rm -rf tmp
echo

echo 'Done.'
echo