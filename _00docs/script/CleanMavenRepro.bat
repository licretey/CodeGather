@echo off
rem create by NettQun

rem 这里写仓库路径
set REPOSITORY_PATH=D:\\java\\mavenRepository
rem 正在搜索
for /f "delims=" %%i in ('dir /b /s "%REPOSITORY_PATH%\*lastUpdated*"') do (
    echo %%i
    del /s /q "%%i"
)
rem 搜索完毕
pause