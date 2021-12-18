# ElectronL
ElectronL is a Cellular Automata game where you create machines from simple parts.
## How To Play
To play ElectronL simply download the latest package and run the main.jar file.<br> Alternatively, if you really don't want to download anything you can play ElectronL at https://replit.com/@scratchdragon10/Electronl. Keep in mind that the game runs pretty bad in replit.
## Issues
ElectronL is VERY early on in development so issues are likely to be common. If you encounter an issue please post it in the issues tab of the projects github.
## Current Modules
#### ElectronL
States (3): Lit, Unlit, Drained<br>
Behavior:
When a lit ElectronL is adjacent to an unlit ElectronL the lit ElectronL will become drained and the unlit ElectronL will become lit.
Drained ElectronL's will return to being unlit the next cycle.
#### Mover
States (2): Active, Inactive<br>
Behavior:
When an inactive mover is adjacent to a lit ElectronL it will become active and move 1 space in the opposite direction to the lit ElectronL,
any modules in the way of the mover will also be moved.
#### Power Cell
States (2): Charged, Uncharged<br>
Charge: 0 - 32<br>
Behavior:
When a power cell's charge reaches 0 its state will change to uncharged and when a power cell's charge reaches 32 it will become charged.<br>Charged power cells will output energy at a rate of 1 charge per 3 cycles. Uncharged power cells will receive charge at a max rate of 1 charge per cycle.
#### Grabber
States (3): Active, Inactive, Powered<br>
Charge: 0 - 8<br>
Behavior:
When an inactive grabber is powered it becomes active and will gain a charge of 8, the Grabber will remain active until it is powered again or loses all of its charges. An active Grabber will lose charges at a rate of 1 per cycle. When an active Grabber is powered it will become a powered Grabber for one cycle whereupon returning to an active grabber it will gain 1 charge. Powered Grabbers can transfer power to an ElectronL but no other modules.
#### Diode
States (2): Lit and Unlit<br>
Types (2): Input and Output<br>
Behavior:
When an unlit input diode is next to a lit ElectronL it will become a lit input diode for 2 cycles. When an unlit output diode is next to a lit input diode it will become a lit output diode for 1 cycle. Only lit output diodes can transfer power out.