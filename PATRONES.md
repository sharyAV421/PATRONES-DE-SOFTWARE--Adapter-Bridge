# Design Patterns

## Bridge

The Bridge pattern is used to separate controllers from game devices.

`Controller` is the abstraction and `GameDevice` is the implementation.

The same controller can work with different devices such as:

- Arcade Machine
- Racing Machine
- Legacy Arcade Machine

This allows the system to add new controllers or devices independently.

## Adapter

The Adapter pattern is used to integrate the Legacy Arcade Machine.

The legacy machine has different methods:

- powerOn()
- powerOff()
- moveJoystickUp()
- moveJoystickDown()

`LegacyArcadeAdapter` converts these methods into the `GameDevice` interface.

This allows the old machine to work with the new controller system without modifying its original code.

## Conclusion

Bridge separates controllers from devices, while Adapter allows an incompatible legacy device to work with the existing system.