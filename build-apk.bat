@echo off
echo 正在构建APK文件...

REM 设置环境变量
set ANDROID_HOME=C:\Android\Sdk
set JAVA_HOME=C:\Program Files\Java\jdk-17

REM 设置Gradle用户目录
set GRADLE_USER_HOME=C:\Users\Administrator\.gradle

REM 创建必要的目录
if not exist "%GRADLE_USER_HOME%\wrapper\dists" mkdir "%GRADLE_USER_HOME%\wrapper\dists"

REM 运行Gradle构建
echo 开始构建...
call gradlew.bat assembleDebug

if %ERRORLEVEL% EQU 0 (
    echo APK构建成功！
    echo APK文件位置: app\build\outputs\apk\debug\app-debug.apk
) else (
    echo APK构建失败！
)

pause 