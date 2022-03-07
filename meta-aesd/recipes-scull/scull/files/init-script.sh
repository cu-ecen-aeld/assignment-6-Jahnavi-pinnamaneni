#! /bin/sh


case "$1" in
    start)
        echo "Starting init script for Module Loading"
        /usr/bin/scull_load
        ;;
    stop)
        echo "Removing user modules"
        /usr/bin/scull_unload
        ;;
    *)
        echo "Usage: $0 {start|stop}"
    exit 1
esac
exit 0
