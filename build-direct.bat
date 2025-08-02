@echo off
echo 正在构建APK文件...

REM 设置环境变量
set ANDROID_HOME=C:\Android\Sdk
set GRADLE_USER_HOME=C:\Users\Administrator\.gradle

REM 创建完整的目录结构
if not exist "%GRADLE_USER_HOME%\wrapper\dists\gradle-8.2-bin\bbg7u40eoinfdyxsxr3z4i7ta" (
    mkdir "%GRADLE_USER_HOME%\wrapper\dists\gradle-8.2-bin\bbg7u40eoinfdyxsxr3z4i7ta"
)

REM 下载Gradle分发包（如果不存在）
if not exist "%GRADLE_USER_HOME%\wrapper\dists\gradle-8.2-bin\bbg7u40eoinfdyxsxr3z4i7ta\gradle-8.2-bin.zip" (
    echo 下载Gradle分发包...
    powershell -Command "Invoke-WebRequest -Uri 'https://services.gradle.org/distributions/gradle-8.2-bin.zip' -OutFile '%GRADLE_USER_HOME%\wrapper\dists\gradle-8.2-bin\bbg7u40eoinfdyxsxr3z4i7ta\gradle-8.2-bin.zip'"
)

REM 运行Gradle构建
echo 开始构建APK...
java -cp gradle\wrapper\gradle-wrapper.jar org.gradle.wrapper.GradleWrapperMain assembleDebug

if %ERRORLEVEL% EQU 0 (
    echo APK构建成功！
    echo APK文件位置: app\build\outputs\apk\debug\app-debug.apk
) else (
    echo APK构建失败！
)

pause 