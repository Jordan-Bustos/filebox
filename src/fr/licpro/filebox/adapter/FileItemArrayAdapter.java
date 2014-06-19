package fr.licpro.filebox.adapter;

import java.util.List;

import fr.licpro.filebox.R;
import fr.licpro.filebox.R.drawable;
import fr.licpro.filebox.dto.commons.FileDto;
import fr.licpro.filebox.dto.commons.ViewHolder;
import fr.licpro.filebox.dto.enums.FileTypeEnum;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FileItemArrayAdapter extends ArrayAdapter<FileDto> 
{
	/**
	 * The list of FileDto items.
	 */
	private final List<FileDto>	mValues;

	/* _________________________________________________________ */
	/**
	 * @param context
	 *            Le context
	 * @param values
	 *            La liste de Plot
	 */
	public FileItemArrayAdapter(final Context context,
			final List<FileDto> values)
	{
		super(context, R.layout.file_item, values);
		mValues = values;
	}

	/* _________________________________________________________ */
	/**
	 * @return
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount()
	{
		return mValues.size();
	}

	/* _________________________________________________________ */
	/**
	 * @param location
	 * @return
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public FileDto getItem(final int location)
	{
		return mValues.get(location);
	}

	/* _________________________________________________________ */
	/**
	 * @param location
	 * @return
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(final int location)
	{
		//TODO
		return mValues.get(location).getHashId().hashCode();	//A verifier !
	}

	/* _________________________________________________________ */
	/**
	 * @param arg0
	 * @return
	 * @see android.widget.Adapter#getItemViewType(int)
	 */
	@Override
	public int getItemViewType(final int arg0)
	{
		return 0;
	}

	/* _________________________________________________________ */
	/**
	 * @param position
	 * @param convertView
	 * @param parent
	 * @return convertView
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 *      android.view.ViewGroup)
	 */
	@Override
	public View getView(final int position, View convertView,
			final ViewGroup parent)
	{
		ViewHolder holder;
		if (convertView == null)
		{
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.file_item, null);
			holder = new ViewHolder();
			holder.mName = (TextView) convertView.findViewById(R.id.name);
			holder.mType = (ImageView) convertView.findViewById(R.id.imageView1);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		final FileDto s = mValues.get(position);
		holder.mName.setText(s.getName());
		if (s.isIsFolder())
			holder.mType.setImageDrawable(getContext().getResources().getDrawable(drawable.dossier));
		else if(s.getFileType().equals(FileTypeEnum.HTML))
			holder.mType.setImageDrawable(getContext().getResources().getDrawable(drawable.html));
		else if(s.getFileType().equals(FileTypeEnum.JPEG))
			holder.mType.setImageDrawable(getContext().getResources().getDrawable(drawable.jpeg));
		else if(s.getFileType().equals(FileTypeEnum.PNG))
			holder.mType.setImageDrawable(getContext().getResources().getDrawable(drawable.png));
		else if(s.getFileType().equals(FileTypeEnum.PDF))
			holder.mType.setImageDrawable(getContext().getResources().getDrawable(drawable.pdf));
		else if(s.getFileType().equals(FileTypeEnum.MP3))
			holder.mType.setImageDrawable(getContext().getResources().getDrawable(drawable.mp3));
		else if(s.getFileType().equals(FileTypeEnum.VCARD))
			holder.mType.setImageDrawable(getContext().getResources().getDrawable(drawable.vcard));
		else //Document txt
			holder.mType.setImageDrawable(getContext().getResources().getDrawable(drawable.fichier));
		return convertView;
	}

	/* _________________________________________________________ */
	/**
	 * @return
	 * @see android.widget.Adapter#getViewTypeCount()
	 */
	@Override
	public int getViewTypeCount()
	{
		return 1;
	}

	/* _________________________________________________________ */
	/**
	 * @return
	 * @see android.widget.Adapter#hasStableIds()
	 */
	@Override
	public boolean hasStableIds()
	{
		return true;
	}

	/* _________________________________________________________ */
	/**
	 * @return
	 * @see android.widget.Adapter#isEmpty()
	 */
	@Override
	public boolean isEmpty()
	{
		return mValues.isEmpty();
	}

	/* _________________________________________________________ */
	/**
	 * @param arg0
	 * @see android.widget.Adapter#registerDataSetObserver(android.database.DataSetObserver)
	 */
	@Override
	public void registerDataSetObserver(final DataSetObserver arg0)
	{
	}

	/* _________________________________________________________ */
	/**
	 * @param arg0
	 * @see android.widget.Adapter#unregisterDataSetObserver(android.database.DataSetObserver)
	 */
	@Override
	public void unregisterDataSetObserver(final DataSetObserver arg0)
	{
	}

	/* _________________________________________________________ */
	/**
	 * @return
	 * @see android.widget.ListAdapter#areAllItemsEnabled()
	 */
	@Override
	public boolean areAllItemsEnabled()
	{
		return true;
	}

	/* _________________________________________________________ */
	/**
	 * @param position
	 * @return
	 * @see android.widget.ListAdapter#isEnabled(int)
	 */
	@Override
	public boolean isEnabled(final int position)
	{
		return true;
	}
}
