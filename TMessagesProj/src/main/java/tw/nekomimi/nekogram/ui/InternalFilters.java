package tw.nekomimi.nekogram.ui;

import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;

import java.util.LinkedList;

public class InternalFilters {

    public static LinkedList<TLRPC.TL_dialogFilterSuggested> internalFilters = new LinkedList<>();

    public static final TLRPC.DialogFilter usersFilter;

    public static final TLRPC.DialogFilter contactsFilter;

    public static final TLRPC.DialogFilter groupsFilter;

    public static final TLRPC.DialogFilter channelsFilter;

    public static final TLRPC.DialogFilter botsFilter;

    public static final TLRPC.DialogFilter unmutedFilter;

    public static final TLRPC.DialogFilter unreadFilter;

    public static final TLRPC.DialogFilter unmutedAndUnreadFilter;

    static {

        usersFilter = mkFilter(LocaleController.getString(R.string.FilterNameUsers),
                LocaleController.getString(R.string.FilterNameUsersDescription),
                MessagesController.DIALOG_FILTER_FLAG_CONTACTS |
                        MessagesController.DIALOG_FILTER_FLAG_NON_CONTACTS |
                        MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_ARCHIVED,
                (it) -> {

                    it.contacts = true;
                    it.non_contacts = true;
                    it.exclude_archived = true;

                });

        contactsFilter = mkFilter(LocaleController.getString(R.string.FilterNameContacts),
                LocaleController.getString(R.string.FilterNameContactsDescription),
                MessagesController.DIALOG_FILTER_FLAG_CONTACTS |
                        MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_ARCHIVED,
                (it) -> {

                    it.contacts = true;
                    it.exclude_archived = true;

                });

        groupsFilter = mkFilter(LocaleController.getString(R.string.FilterNameGroups),
                LocaleController.getString(R.string.FilterNameGroupsDescription),
                MessagesController.DIALOG_FILTER_FLAG_GROUPS |
                        MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_ARCHIVED,
                (it) -> {

                    it.groups = true;
                    it.exclude_archived = true;

                });

        channelsFilter = mkFilter(LocaleController.getString(R.string.FilterNameChannels),
                LocaleController.getString(R.string.FilterNameChannelsDescription),
                MessagesController.DIALOG_FILTER_FLAG_CHANNELS |
                        MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_ARCHIVED,
                (it) -> {

                    it.broadcasts = true;
                    it.exclude_archived = true;

                });

        botsFilter = mkFilter(LocaleController.getString(R.string.FilterNameBots),
                LocaleController.getString(R.string.FilterNameBotsDescription),
                MessagesController.DIALOG_FILTER_FLAG_BOTS |
                        MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_ARCHIVED,
                (it) -> {

                    it.bots = true;
                    it.exclude_archived = true;

                });

        unmutedFilter = mkFilter(LocaleController.getString(R.string.FilterNameUnmuted),
                LocaleController.getString(R.string.FilterNameUnmutedDescription),
                MessagesController.DIALOG_FILTER_FLAG_CONTACTS |
                        MessagesController.DIALOG_FILTER_FLAG_NON_CONTACTS |
                        MessagesController.DIALOG_FILTER_FLAG_GROUPS |
                        MessagesController.DIALOG_FILTER_FLAG_CHANNELS |
                        MessagesController.DIALOG_FILTER_FLAG_BOTS |
                        MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_MUTED |
                        MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_ARCHIVED,
                (it) -> {

                    it.contacts = true;
                    it.non_contacts = true;
                    it.groups = true;
                    it.broadcasts = true;
                    it.bots = true;
                    it.exclude_muted = true;
                    it.exclude_archived = true;

                });

        unreadFilter = mkFilter(LocaleController.getString(R.string.FilterNameUnread2),
                LocaleController.getString(R.string.FilterNameUnreadDescription),
                MessagesController.DIALOG_FILTER_FLAG_CONTACTS |
                        MessagesController.DIALOG_FILTER_FLAG_NON_CONTACTS |
                        MessagesController.DIALOG_FILTER_FLAG_GROUPS |
                        MessagesController.DIALOG_FILTER_FLAG_CHANNELS |
                        MessagesController.DIALOG_FILTER_FLAG_BOTS |
                        MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_READ |
                        MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_ARCHIVED,
                (it) -> {

                    it.contacts = true;
                    it.non_contacts = true;
                    it.groups = true;
                    it.broadcasts = true;
                    it.bots = true;
                    it.exclude_read = true;
                    it.exclude_archived = true;

                });

        unmutedAndUnreadFilter = mkFilter(LocaleController.getString(R.string.FilterNameUnmutedAndUnread),
                LocaleController.getString(R.string.FilterNameUnmutedAndUnreadDescription),
                MessagesController.DIALOG_FILTER_FLAG_CONTACTS |
                        MessagesController.DIALOG_FILTER_FLAG_NON_CONTACTS |
                        MessagesController.DIALOG_FILTER_FLAG_GROUPS |
                        MessagesController.DIALOG_FILTER_FLAG_CHANNELS |
                        MessagesController.DIALOG_FILTER_FLAG_BOTS |
                        MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_MUTED |
                        MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_READ |
                        MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_ARCHIVED,
                (it) -> {

                    it.contacts = true;
                    it.non_contacts = true;
                    it.groups = true;
                    it.broadcasts = true;
                    it.bots = true;
                    it.exclude_muted = true;
                    it.exclude_read = true;
                    it.exclude_archived = true;

                });

    }

    @FunctionalInterface
    interface FilterBuilder {

        void apply(TLRPC.DialogFilter filter);

    }

    private static int currId = 10;

    private static TLRPC.DialogFilter mkFilter(String name, String description, int flag, FilterBuilder builder) {

        TLRPC.TL_dialogFilterSuggested suggestedFilter = new TLRPC.TL_dialogFilterSuggested();

        suggestedFilter.description = description != null ? description : "Nya ~";

        suggestedFilter.filter = new TLRPC.TL_dialogFilter();

        suggestedFilter.filter.id = currId;

        suggestedFilter.filter.title = new TLRPC.TL_textWithEntities();
        suggestedFilter.filter.title.text = name;
        suggestedFilter.filter.flags = flag;

        builder.apply(suggestedFilter.filter);

        internalFilters.add(suggestedFilter);

        currId++;

        return suggestedFilter.filter;

    }

}
