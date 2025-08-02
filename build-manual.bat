@echo off
echo 正在构建APK文件...

REM 设置环境变量
set ANDROID_HOME=C:\Android\Sdk
set GRADLE_USER_HOME=C:\Users\Administrator\.gradle

REM 解压Gradle（如果还没有解压）
if not exist "%GRADLE_USER_HOME%\wrapper\dists\gradle-8.2-bin\bbg7u40eoinfdyxsxr3z4i7ta\gradle-8.2" (
    echo 解压Gradle...
    powershell -Command "Expand-Archive -Path '%GRADLE_USER_HOME%\wrapper\dists\gradle-8.2-bin\bbg7u40eoinfdyxsxr3z4i7ta\gradle-8.2-bin.zip' -DestinationPath '%GRADLE_USER_HOME%\wrapper\dists\gradle-8.2-bin\bbg7u40eoinfdyxsxr3z4i7ta' -Force"
)

REM 直接使用解压后的Gradle
echo 开始构建APK...
"%GRADLE_USER_HOME%\wrapper\dists\gradle-8.2-bin\bbg7u40eoinfdyxsxr3z4i7ta\gradle-8.2\bin\gradle.bat" assembleDebug

if %ERRORLEVEL% EQU 0 (
    echo APK构建成功！
    echo APK文件位置: app\build\outputs\apk\debug\app-debug.apk
) else (
    echo APK构建失败！
)

pause 