# Electronl
Electronl is a Cellular Automata game where you create machines from simple parts.
## Issues
Electronl is VERY early on in development so issues are likely to be comman. If you encounter an issue please post it in the issues tab of the projects github.
## Current Modules
<u>ElectronL</u>
ElectronL modules have 3 states: unlit, lit and drained. When a lit ElectronL is adjacent to an unlit ElectronL the lit ElectronL will become drained and the unlit ElectronL will become lit.
Drained ElectronL's will return to being unlit the next cycle.<br><br>
<u>Mover</u>
Movers have 2 states: active and inactive. When an inactive mover is adjacent to a lit ElectronL it will become active and move 1 space in the opposite direction to the lit ElectronL,
any modules in the way of the mover will also be moved.
