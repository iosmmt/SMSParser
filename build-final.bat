@echo off
echo 正在构建APK文件...

REM 设置环境变量
set ANDROID_HOME=C:\Android\Sdk

REM 尝试使用系统Gradle
echo 尝试使用系统Gradle...
gradle --version >nul 2>&1
if %ERRORLEVEL% EQU 0 (
    echo 找到系统Gradle，开始构建...
    gradle assembleDebug
    goto :check_result
)

REM 尝试使用Android Studio的Gradle
echo 尝试使用Android Studio的Gradle...
if exist "C:\Program Files\Android\Android Studio\gradle\gradle-8.2\bin\gradle.bat" (
    echo 找到Android Studio Gradle，开始构建...
    "C:\Program Files\Android\Android Studio\gradle\gradle-8.2\bin\gradle.bat" assembleDebug
    goto :check_result
)

REM 尝试使用用户目录下的Gradle
echo 尝试使用用户目录下的Gradle...
if exist "%USERPROFILE%\.gradle\wrapper\dists\gradle-8.2-bin\*\gradle-8.2\bin\gradle.bat" (
    for /d %%i in ("%USERPROFILE%\.gradle\wrapper\dists\gradle-8.2-bin\*") do (
        if exist "%%i\gradle-8.2\bin\gradle.bat" (
            echo 找到用户目录Gradle，开始构建...
            "%%i\gradle-8.2\bin\gradle.bat" assembleDebug
            goto :check_result
        )
    )
)

echo 未找到可用的Gradle，请安装Android Studio或Gradle
goto :end

:check_result
if %ERRORLEVEL% EQU 0 (
    echo APK构建成功！
    if exist "app\build\outputs\apk\debug\app-debug.apk" (
        echo APK文件位置: app\build\outputs\apk\debug\app-debug.apk
        echo APK文件大小:
        dir "app\build\outputs\apk\debug\app-debug.apk"
    ) else (
        echo 警告：未找到APK文件
    )
) else (
    echo APK构建失败！
)

:end
pause 