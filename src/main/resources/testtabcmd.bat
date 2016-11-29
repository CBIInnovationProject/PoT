@echo off
SET tabServerAddr=%1
SET username=%2
SET password=%3
SET siteUrl=%4
SET dashboardPng=%5
SET targetFile=%6
tabcmd login -s %tabServerAddr% -u %username% -p %password% -t %siteUrl%
::tabcmd get %dashboardPng% -f %targetFile%
tabcmd export %dashboardPng% --csv -f %targetFile%
tabcmd logout
exit
:end