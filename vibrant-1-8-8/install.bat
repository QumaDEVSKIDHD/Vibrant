:: #############################################################################
:: A batch file to download and execute MCP, rearrange the generated code and
:: setup the Minecraft module for Vibrant
::
:: The MCP version can be changed by setting $MCP_RL to a different release
:: But in that case, the installed libraries must be updated as well
::
:: Author: Cydhra
:: #############################################################################

@ECHO OFF
TITLE Install Minecraft Module
COLOR 0F
SETLOCAL EnableDelayedExpansion
CLS

:: ################# CONSTANT POOL #################
SET MCP_RL=http://www.modcoderpack.com/files/mcp918.zip
SET MC_VER=1.8.8
:: ################# END OF CONSTANT POOL #################
ECHO Downloading MCP...
DEL tmp /S /Q /F
MD tmp
CD tmp
powershell "(New-Object Net.WebClient).DownloadFile('%MCP_RL%', 'mcp.zip')"
ECHO.

ECHO Unzipping MCP...
powershell "Expand-Archive mcp.zip -DestinationPath ."
DEL mcp.zip
ECHO.

ECHO Executing MCP...
runtime\bin\python\python_mcp runtime\decompile.py %*
CD ..
ECHO.

ECHO Copying Sources...
ROBOCOPY tmp\src\minecraft\ src\main\java /E
ECHO.

ECHO Rearrange...
MOVE src\main\java\Start.java src\main\java\net\minecraft\Start.java
ECHO.

ECHO Copying Libraries...
ROBOCOPY tmp\jars\libraries\com\mojang\authlib\1.5.21\ lib\com\mojang\authlib\1.5.21\ /MIR
ROBOCOPY tmp\jars\libraries\com\paulscode\codecjorbis\20101023\ lib\com\paulscode\codecjorbis\20101023\ /MIR
ROBOCOPY tmp\jars\libraries\com\paulscode\codecwav\20101023\codecwav-20101023.jar lib\com\paulscode\codecwav\20101023\codecwav-20101023.jar /MIR
ROBOCOPY tmp\jars\libraries\com\paulscode\libraryjavasound\20101123\ lib\com\paulscode\libraryjavasound\20101123\ /MIR
ROBOCOPY tmp\jars\libraries\com\paulscode\librarylwjglopenal\20100824\ lib\com\paulscode\librarylwjglopenal\20100824\ /MIR
ROBOCOPY tmp\jars\libraries\com\paulscode\soundsystem\20120107\ lib\com\paulscode\soundsystem\20120107\ /MIR
ROBOCOPY tmp\jars\libraries\oshi-project\oshi-core\1.1\ lib\oshi-project\oshi-core\1.1\ /MIR
ROBOCOPY tmp\jars\libraries\tv\twitch lib\tv\twitch /MIR

ECHO.

ECHO Copying workspace...
ROBOCOPY tmp\jars\ workspace\ /MIR
ECHO.

ECHO Cleaning Up...
RMDIR tmp /S /Q
ECHO.

ECHO Done.
ECHO.

PAUSE