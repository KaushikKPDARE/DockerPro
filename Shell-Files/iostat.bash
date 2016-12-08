#!/bin/bash
sleep 5
iostat -x 1 100 |tee /home/dockuser/dockpro/iostat1.txt
