# 美食外卖 Android 应用

一个简单的Android外卖平台应用，作为结课作业项目。

## 项目简介

本项目是一个基础的Android外卖平台应用，实现了以下核心功能：

- 浏览餐厅列表
- 查看餐厅详情和菜单
- 添加菜品到购物车
- 管理购物车
- 结算功能

## 技术栈

- **开发语言**: Java
- **最低SDK版本**: Android 7.0 (API 24)
- **目标SDK版本**: Android 14 (API 34)
- **架构模式**: MVC
- **UI组件**: RecyclerView, CardView
- **数据存储**: 内存存储（单例模式）

## 项目结构

```
FoodDelivery/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/fooddelivery/
│   │   │   │   ├── model/           # 数据模型
│   │   │   │   │   ├── Restaurant.java
│   │   │   │   │   ├── MenuItem.java
│   │   │   │   │   └── CartItem.java
│   │   │   │   ├── adapter/         # 适配器
│   │   │   │   │   ├── RestaurantAdapter.java
│   │   │   │   │   ├── MenuItemAdapter.java
│   │   │   │   │   └── CartAdapter.java
│   │   │   │   ├── utils/           # 工具类
│   │   │   │   │   └── CartManager.java
│   │   │   │   ├── MainActivity.java
│   │   │   │   ├── RestaurantDetailActivity.java
│   │   │   │   └── CartActivity.java
│   │   │   ├── res/
│   │   │   │   ├── layout/          # 布局文件
│   │   │   │   ├── values/          # 资源文件
│   │   │   │   └── xml/             # XML配置
│   │   │   └── AndroidManifest.xml
│   │   └── build.gradle
│   └── proguard-rules.pro
├── build.gradle
├── settings.gradle
└── gradle.properties
```

## 功能说明

### 1. 主页面 (MainActivity)
- 显示餐厅列表
- 每个餐厅卡片显示：餐厅图片、名称、菜系、评分、配送时间、配送费
- 点击餐厅卡片进入餐厅详情页
- 底部显示购物车按钮（有商品时显示）

### 2. 餐厅详情页 (RestaurantDetailActivity)
- 显示餐厅详细信息
- 显示菜单列表
- 每个菜品可以添加到购物车或从购物车移除
- 显示当前购物车商品数量和总价

### 3. 购物车页面 (CartActivity)
- 显示购物车中的所有商品
- 显示每个商品的数量和小计
- 显示总价
- 可以结算或清空购物车

## 数据模型

### Restaurant (餐厅)
- id: 餐厅ID
- name: 餐厅名称
- category: 菜系分类
- rating: 评分
- imageResId: 图片资源ID
- deliveryTime: 配送时间
- deliveryFee: 配送费

### MenuItem (菜品)
- id: 菜品ID
- name: 菜品名称
- description: 菜品描述
- price: 价格
- imageResId: 图片资源ID
- restaurantId: 所属餐厅ID

### CartItem (购物车项)
- menuItem: 菜品信息
- quantity: 数量

## 如何运行

1. 确保已安装Android Studio
2. 打开Android Studio，选择"Open an existing Android Studio project"
3. 选择FoodDelivery文件夹
4. 等待Gradle同步完成
5. 连接Android设备或启动模拟器
6. 点击运行按钮

## 注意事项

1. 本项目使用占位图片（颜色块），实际项目中应替换为真实图片
2. 数据存储在内存中，应用关闭后数据会丢失
3. 这是一个简化版本，适合作为学习项目
4. 如需扩展功能，可以考虑：
   - 添加网络请求获取真实数据
   - 使用数据库持久化存储
   - 添加用户登录功能
   - 添加订单管理功能
   - 添加支付功能

## 开发环境

- Android Studio Hedgehog | 2023.1.1 或更高版本
- Gradle 8.0 或更高版本
- JDK 17 或更高版本

## 作者

学生结课作业项目

## 许可证

本项目仅用于学习目的
