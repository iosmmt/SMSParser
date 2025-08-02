# 上传项目到GitHub指南

## 第一步：创建GitHub仓库

1. **访问GitHub**
   - 打开浏览器，访问：https://github.com
   - 登录您的GitHub账户

2. **创建新仓库**
   - 点击右上角的 "+" 号
   - 选择 "New repository"
   - 填写仓库信息：
     - Repository name: `SMSParser`
     - Description: `Android短信解析器应用`
     - 选择 "Public"（公开）
     - **不要勾选** "Add a README file"
     - **不要勾选** "Add .gitignore"
     - **不要勾选** "Choose a license"
   - 点击 "Create repository"

## 第二步：上传项目文件

### 方法A：使用GitHub网页上传

1. **在新建的仓库页面**
   - 您会看到 "uploading an existing file" 链接
   - 点击该链接

2. **上传所有项目文件**
   - 将以下文件和文件夹拖拽到上传区域：

   **必需的核心文件：**
   - `app/` 文件夹（整个文件夹）
   - `gradle/` 文件夹（整个文件夹）
   - `.github/` 文件夹（整个文件夹）
   - `build.gradle`
   - `settings.gradle`
   - `gradle.properties`
   - `gradlew`
   - `gradlew.bat`
   - `README.md`

   **可选文件：**
   - `build-*.bat` 文件（构建脚本）
   - `如何构建APK.md`
   - `上传到GitHub指南.md`

3. **提交文件**
   - 在页面底部填写：
     - Commit message: `Initial commit: 短信解析器Android应用`
   - 点击 "Commit changes"

### 方法B：使用GitHub Desktop（如果已安装）

1. **下载GitHub Desktop**
   - 访问：https://desktop.github.com/
   - 下载并安装

2. **添加本地仓库**
   - 打开GitHub Desktop
   - 点击 "File" → "Add local repository"
   - 选择项目文件夹
   - 点击 "Create repository"
   - 填写仓库信息并发布

## 第三步：触发GitHub Actions构建

1. **查看Actions**
   - 在仓库页面点击 "Actions" 标签
   - 您应该能看到 "Build APK" 工作流

2. **手动触发构建**
   - 点击 "Build APK" 工作流
   - 点击 "Run workflow" 按钮
   - 选择分支（通常是 main）
   - 点击 "Run workflow"

## 第四步：监控构建过程

GitHub Actions将执行以下步骤：
1. ✅ Checkout code - 检出代码
2. ✅ Set up JDK 17 - 设置Java环境
3. ✅ Cache Gradle packages - 缓存Gradle包
4. ✅ Grant execute permission for gradlew - 设置执行权限
5. ✅ Build APK - 构建APK文件
6. ✅ Upload APK - 上传APK作为构建产物

## 第五步：下载APK文件

构建完成后：
1. 在Actions页面点击成功的构建记录
2. 在页面底部找到 "Artifacts" 部分
3. 点击 "app-debug" 下载APK文件

## 预期时间
- 首次构建：5-10分钟
- 后续构建：2-3分钟

## 项目功能
构建的APK将包含：
- ✅ 短信权限智能申请
- ✅ 自定义规则设置页面
- ✅ 正则规则设置页面
- ✅ 现代化Material Design界面
- ✅ 友好的权限处理

## 故障排除
如果遇到问题：
1. 确保所有必需文件都已上传
2. 检查构建日志中的错误信息
3. 重新触发构建 