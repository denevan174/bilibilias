# iOS 导入产物

当前仓库通过聚合模块 `:shared` 提供 iOS 导入产物流水线，目标是产出一个可直接被 Xcode 导入的 `XCFramework` 单入口产物。

## 覆盖模块

当前默认产出以下模块的 iOS 导入产物：

- `shared`

`shared` 内部聚合 `core:data`，并通过 `core:data` 的公开依赖向上暴露 `core:database`、`core:datastore`、`core:network` 等能力。iOS 工程应优先只导入这一份产物，而不是分别拖入多个 `core:*` 框架。

## 本地构建

构建产物：

```bash
./gradlew assembleSharedIosArtifacts
```

产物目录：

```text
shared/build/XCFrameworks/release/
```

当前默认会生成一个聚合 `XCFramework` 目录：

- `ASShared.xcframework`

## CI 流水线

GitHub Actions 工作流文件：

```text
.github/workflows/build-core-ios-artifacts.yml
```

流水线运行在 `macos-latest`，执行：

```bash
./gradlew assembleSharedIosArtifacts --stacktrace --info
```

随后直接上传 `shared/build/XCFrameworks/release/ASShared.xcframework` 作为构建产物。

## 说明

- 当前流水线默认面向 iOS 单入口产物，优先通过 `ASShared.xcframework` 交付。
- Kotlin 工程内部仍保持 `core:*` 多模块结构；`shared` 只负责对 iOS 聚合导出。
- 不再额外把产物复制到 `core/build/ios-artifacts`；默认直接使用 `shared` 模块原生输出目录。
- iOS 侧初始化和 bridge 入口也应优先从 `shared` 暴露；若直接通过 repository/bridge 访问，当前实现也会自动完成 Koin 懒启动。
- 若后续新增需要暴露给 iOS 的 KMP 模块，优先评估是否应作为 `shared` 的依赖导出，并同步更新根项目任务和该文档。
