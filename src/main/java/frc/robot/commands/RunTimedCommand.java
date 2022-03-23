package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class RunTimedCommand extends RunCommand {
    private final Timer timer = new Timer();
    private final double maxSeconds;

    /**
     * Creates a new RunCommand. The Runnable will be run continuously until the command ends. Does
     * not run when disabled.
     *
     * @param toRun        the Runnable to run
     * @param requirements the subsystems to require
     */
    public RunTimedCommand(double seconds, Runnable toRun, Subsystem... requirements) {
        super(toRun, requirements);

        maxSeconds = seconds;
    }

    @Override
    public void initialize() {
        super.initialize();

        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public boolean isFinished() {
        return timer.get() > maxSeconds;
    }
}
