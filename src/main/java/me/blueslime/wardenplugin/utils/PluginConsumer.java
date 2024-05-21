package me.blueslime.wardenplugin.utils;

import java.util.function.Consumer;

@SuppressWarnings("CallToPrintStackTrace")
public interface PluginConsumer<T> {
    T executeConsumer() throws Exception;

    static void process(PluginOutConsumer consumer) {
        try {
            consumer.executeConsumer();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    static void process(String message, PluginOutConsumer consumer) {
        try {
            consumer.executeConsumer();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    static void process(PluginConsumer.PluginOutConsumer consumer, Consumer<Exception> exception) {
        try {
            consumer.executeConsumer();
        } catch (Exception var3) {
            exception.accept(var3);
        }

    }

    static <T> T ofUnchecked(PluginConsumer<T> template) {
        T results = null;

        try {
            results = template.executeConsumer();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return results;
    }

    static <T> T ofUnchecked(PluginConsumer<T> template, Consumer<Exception> exception, PluginExecutableConsumer<T> defValue) {
        T results = null;

        try {
            results = template.executeConsumer();
        } catch (Exception var5) {
            if (defValue != null) {
                results = defValue.accept();
            }
            exception.accept(var5);
        }

        return results;
    }

    static <T> T ofUnchecked(PluginConsumer<T> template, Consumer<Exception> exception) {
        T results = null;

        try {
            results = template.executeConsumer();
        } catch (Exception var4) {
            exception.accept(var4);
        }

        return results;
    }

    static <T> T ofUnchecked(PluginConsumer<T> template, T defValue) {
        T results = defValue;

        try {
            results = template.executeConsumer();
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return results;
    }

    static <T> T ofUnchecked(String message, PluginConsumer<T> template) {
        T results = null;

        try {
            results = template.executeConsumer();
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return results;
    }

    static <T> T ofUnchecked(String message, PluginConsumer<T> template, T defValue) {
        T results = defValue;

        try {
            results = template.executeConsumer();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return results;
    }

    static <T> PluginConsumer<T> of(PluginConsumer<T> c) {
        return c;
    }

    interface PluginExecutableConsumer<T> {
        T accept();
    }

    interface PluginOutConsumer {
        void executeConsumer() throws Exception;
    }
}

