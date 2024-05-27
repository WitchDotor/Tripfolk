# Ui kit

Shared module responsible for the UI theme and components.

Module contains:

- App theme that is common for the whole project.
- Components that are used in any other presentation layer.
- Components' theme that encapsulates all the style of the component.

## Structure

- Each component has its own folder with component itself and its theme.
- Each component and theme should have a name that starts with `Ui` prefix. It helps to find components in the project.
- App Theme is located in the theme folder and provides common attributes like colors and typography.

```
:ui-kit
├── component
    ├── button
        ├── UiButton.kt
        └── UiButtonTheme.kt
    └── ...
└── theme
    └── UiTheme.kt
```