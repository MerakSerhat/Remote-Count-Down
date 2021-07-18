## Remote Count Down
- This is a mobile count-down timer application that can be controlled from the desktop. You can put your phone somewhere that is far from you but can be seen easily, and set a countdown list with your desktop application. And also you can change the background of your mobile timer and set a title.
- **For example,** 2 hours Pomodoro Timer Set

| **Time**  | **Color**  | **Title**  |
| ------------ | ------------ | ------------ |
|  2 min | Yellow  | Get Ready!  |
|  25 min | Black  | Read Book #1  |
|  5 min | Green  |Relax...   |
|  25min | Black  | Read Book #2  |
|  5 min |  Green | Relax...  |
|  25min | Black  | Read Book #3  |
|  5 min |  Green |  Relax... |
|  25min |  Black | Read Book #4  |

## Aim of this application
When we touch our phones, we tend to glance at notifications and our social media pages, and it distracts our attention. This application provides remote control of our timer so that we don't have to touch our phones and remain concentrated.

## Development
- This application is based on [libGDX](https://libgdx.com/ "libGDX")  framework with java.
- [JNativeHook](https://github.com/kwhat/jnativehook "JNativeHook") library used for detecting some keyboard activities while this application is not focused (For pausing and starting the timer without having to focus on desktop application)
- Also, **java sockets** were used for connecting this application with a mobile one.
