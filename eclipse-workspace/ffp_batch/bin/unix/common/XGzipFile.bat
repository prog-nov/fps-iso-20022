@echo off
setlocal

C:
cd C:\Program Files\WinRAR\

set inputFile=%1
set xFilePath=%2
WinRar.exe x -Y "%inputFile%" "%xFilePath%"

endlocal
exit
