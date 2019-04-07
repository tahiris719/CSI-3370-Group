import { NgModule } from '@angular/core';

import { DungeonsandDatabasesSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [DungeonsandDatabasesSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [DungeonsandDatabasesSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class DungeonsandDatabasesSharedCommonModule {}
