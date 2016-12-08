blktrace -d /dev/dm-0 -o - | blkparse -f "%d,%S,%n,%T\n" -a complete -q -i - |tee /home/dockuser/dockpro/traceblk1.txt
sleep 1000s
kill -TERM $(pgrep -o blktrace)