# 如何构建APK文件

## 方法一：使用Android Studio（推荐）

1. **安装Android Studio**
   - 下载并安装Android Studio：https://developer.android.com/studio
   - 安装过程中会自动下载Android SDK和Gradle

2. **打开项目**
   - 启动Android Studio
   - 选择"Open an existing Android Studio project"
   - 选择当前项目文件夹

3. **构建APK**
   - 点击菜单 Build → Build Bundle(s) / APK(s) → Build APK(s)
   - 或者点击工具栏的绿色运行按钮

4. **找到APK文件**
   - APK文件位置：`app/build/outputs/apk/debug/app-debug.apk`

## 方法二：使用命令行（需要完整环境）

1. **安装Java JDK**
   - 下载并安装Java JDK 17或更高版本
   - 设置JAVA_HOME环境变量

2. **安装Android SDK**
   - 下载Android SDK Command Line Tools
   - 设置ANDROID_HOME环境变量
   - 安装必要的SDK组件：
     ```bash
     sdkmanager "platform-tools" "platforms;android-34" "build-tools;34.0.0"
     ```

3. **安装Gradle**
   - 下载Gradle 8.2或更高版本
   - 设置GRADLE_HOME环境变量
   - 将Gradle bin目录添加到PATH

4. **构建APK**
   ```bash
   gradle assembleDebug
   ```

## 方法三：使用在线构建服务

1. **GitHub Actions**
   - 将项目推送到GitHub
   - 创建GitHub Actions工作流来自动构建APK

2. **其他CI/CD服务**
   - 使用Jenkins、CircleCI等持续集成服务

## 项目文件说明

当前项目包含以下文件：
- `app/build.gradle` - 应用级构建配置
- `build.gradle` - 项目级构建配置
- `settings.gradle` - 项目设置
- `gradle.properties` - Gradle属性配置
- `app/src/main/` - 源代码和资源文件
- `app/src/main/AndroidManifest.xml` - 应用清单文件

## 功能特性

构建的APK将包含以下功能：
1. **短信权限管理** - 智能申请和权限处理
2. **自定义规则设置** - 前后文提取规则
3. **正则规则设置** - 正则表达式测试
4. **现代化UI** - Material Design界面

## 注意事项

- 确保Android设备或模拟器支持API 24或更高版本
- 短信权限需要在真机上测试
- 首次运行可能需要较长时间下载依赖

## 故障排除

如果遇到构建问题：
1. 检查Java版本（需要JDK 17+）
2. 检查Android SDK是否正确安装
3. 检查网络连接（需要下载依赖）
4. 查看Gradle构建日志获取详细错误信息 