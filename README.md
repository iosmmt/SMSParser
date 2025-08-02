# 短信解析器 Android应用

这是一个Android应用，用于解析短信内容，支持自定义规则和正则表达式规则。

## 功能特性

### 1. 短信权限管理
- 申请短信读取权限
- 权限被拒绝时显示友好提示
- 引导用户到设置页面手动开启权限

### 2. 自定义规则设置
- 支持设置"前"和"后"文来提取短信内容
- 实时预览解析结果
- 支持标签、快递电话、取件码、取件地址等字段
- 重置功能和使用功能

### 3. 正则规则设置
- 输入正则表达式进行测试
- 实时显示匹配结果
- 支持捕获组显示
- 保存正则规则

## 项目结构

```
app/
├── src/main/
│   ├── java/com/example/smsparser/
│   │   ├── MainActivity.kt              # 主页面
│   │   └── ui/
│   │       ├── CustomRulesActivity.kt   # 自定义规则页面
│   │       └── RegexRulesActivity.kt    # 正则规则页面
│   ├── res/
│   │   ├── layout/
│   │   │   ├── activity_main.xml        # 主页面布局
│   │   │   ├── activity_custom_rules.xml # 自定义规则布局
│   │   │   └── activity_regex_rules.xml  # 正则规则布局
│   │   ├── values/
│   │   │   ├── strings.xml              # 字符串资源
│   │   │   ├── colors.xml               # 颜色资源
│   │   │   └── themes.xml               # 主题样式
│   │   └── drawable/
│   │       └── edit_text_border.xml     # 输入框边框样式
│   └── AndroidManifest.xml              # 应用清单文件
└── build.gradle                         # 应用级构建配置
```

## 技术栈

- **语言**: Kotlin
- **UI框架**: Android ViewBinding + Material Design
- **权限管理**: Android Runtime Permissions
- **正则表达式**: Java.util.regex.Pattern

## 权限说明

应用需要以下权限：
- `RECEIVE_SMS`: 接收短信
- `READ_SMS`: 读取短信
- `SEND_SMS`: 发送短信

## 使用方法

1. **申请权限**: 在主页面点击"申请短信权限"按钮
2. **自定义规则**: 点击"自定义规则设置"，设置前后文规则
3. **正则规则**: 点击"正则规则设置"，输入正则表达式进行测试

## 自定义规则使用示例

假设短信内容为：`您的快递已到达，取件码是123456，请到XX小区取件`

设置规则：
- 标签前：`快递已到达`
- 标签后：`取件码`
- 取件码前：`取件码是`
- 取件码后：`请`

解析结果：
- 标签：`，`
- 取件码：`123456`

## 构建和运行

1. 使用Android Studio打开项目
2. 同步Gradle依赖
3. 连接Android设备或启动模拟器
4. 点击运行按钮

## 注意事项

- 应用需要Android 6.0 (API 24) 或更高版本
- 短信权限属于危险权限，需要用户手动授权
- 在Android 10及以上版本，某些短信权限可能受到限制

## 开发环境

- Android Studio Hedgehog | 2023.1.1
- Kotlin 1.9.10
- Android Gradle Plugin 8.2.0
- 最低SDK版本：24 (Android 7.0)
- 目标SDK版本：34 (Android 14) 