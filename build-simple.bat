@echo off
echo 正在构建APK文件...

REM 设置环境变量
set ANDROID_HOME=C:\Android\Sdk
set GRADLE_USER_HOME=C:\Users\Administrator\.gradle

REM 创建目录
if not exist "%GRADLE_USER_HOME%\wrapper\dists" mkdir "%GRADLE_USER_HOME%\wrapper\dists"

REM 尝试直接运行Java
java -version
if %ERRORLEVEL% NEQ 0 (
    echo Java未找到，尝试使用系统Java...
    set JAVA_HOME=C:\Program Files\Java\jdk-17
    set PATH=%JAVA_HOME%\bin;%PATH%
)

REM 运行Gradle
echo 开始构建APK...
java -cp gradle\wrapper\gradle-wrapper.jar org.gradle.wrapper.GradleWrapperMain assembleDebug

echo 构建完成！
pause 