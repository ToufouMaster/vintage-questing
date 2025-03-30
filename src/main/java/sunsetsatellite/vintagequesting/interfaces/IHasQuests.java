package sunsetsatellite.vintagequesting.interfaces;

import com.mojang.nbt.tags.CompoundTag;
import sunsetsatellite.vintagequesting.util.QuestGroup;

public interface IHasQuests {

	QuestGroup getQuestGroup();

	void setQuestGroup(QuestGroup group);

	void loadData(CompoundTag tag);

	void resetAll();

	void resetChapter(String id);

	void resetQuest(String id);
}
